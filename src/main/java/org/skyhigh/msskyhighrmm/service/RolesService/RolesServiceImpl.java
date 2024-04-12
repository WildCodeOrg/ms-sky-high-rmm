package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;
import org.skyhigh.msskyhighrmm.repository.RolesOperationsRepository;
import org.skyhigh.msskyhighrmm.repository.UserGroupRolesRepository;
import org.skyhigh.msskyhighrmm.repository.UsersRolesRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolesServiceImpl implements RolesService{
    private static final Map<UUID, UserGroupRole> ROLE_MAP = new HashMap<>();

    private final UserGroupRolesRepository userGroupRolesRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final RolesOperationsRepository rolesOperationsRepository;

    public RolesServiceImpl(UserGroupRolesRepository userGroupRolesRepository, UsersRolesRepository usersRolesRepository, RolesOperationsRepository rolesOperationsRepository) {
        this.userGroupRolesRepository = userGroupRolesRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.rolesOperationsRepository = rolesOperationsRepository;
    }

    @Override
    public UUID addRole(String roleName, String description, boolean isCritical)
    {
        if (userGroupRolesRepository.findByRoleName(roleName) != null) return null;

        return (userGroupRolesRepository
                .save(new UserGroupRolesEntity(
                        null,
                        roleName,
                        description,
                        isCritical)
                )
        ).getId();
    }

    @Override
    public ListOfUserGroupRoles rolesSearch(PaginationInfo paginationInfo, UUID roleId, UserGroupRolesFilters userGroupRolesFilters, UserGroupRolesSort userGroupRolesSort) {
        ArrayList<UserGroupRole> temporaryAllRolesList = new ArrayList<>(ROLE_MAP.values());
        ArrayList<UserGroupRole> resultUserGroupRolesList = new ArrayList<>();

        if (roleId != null) {
            UserGroupRole role = getRoleById(roleId);
            if (role != null) {
                resultUserGroupRolesList.add(role);
                temporaryAllRolesList = new ArrayList<>(resultUserGroupRolesList);
            } else return null;
        } else if (userGroupRolesFilters != null) {
            temporaryAllRolesList = UserGroupRolesFilters.filter(temporaryAllRolesList,
                    userGroupRolesFilters.getRoleName(), userGroupRolesFilters.getDescription(),
                    userGroupRolesFilters.getCriticality());
        }

        if (userGroupRolesSort != null) {
            UserGroupRolesSort.sort(temporaryAllRolesList, userGroupRolesSort);
        }

        int paginationItemCount = temporaryAllRolesList.size();
        int paginationPageNumber = 1;
        int itemCount = temporaryAllRolesList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<UserGroupRole> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, temporaryAllRolesList);
            resultUserGroupRolesList = paginated.getResultList();
        } else {
            resultUserGroupRolesList = new ArrayList<>(temporaryAllRolesList);
        }

        return resultUserGroupRolesList != null
            ? new ListOfUserGroupRoles(itemCount, paginationItemCount, paginationPageNumber, resultUserGroupRolesList)
            : null;
    }

    @Override
    public DeleteUserGroupRoleResultMessage deleteRole(UUID roleId) {
        if (getRoleById(roleId) == null)
            return new DeleteUserGroupRoleResultMessage(
                    "Роли с идентификатором '" + roleId + "' не сущетсвует",
                    1
            );
        if (getRoleById(roleId).is_critical())
            return new DeleteUserGroupRoleResultMessage(
                    "Роль с идентификатором '" + roleId + "' является критичной",
                    2
            );

        ROLE_MAP.remove(roleId);
        return new DeleteUserGroupRoleResultMessage(
                "Роль с идентификатором '" + roleId + "' успешно удалена",
                0
        );
    }

    private UserGroupRole getRoleById(UUID id) {
        return ROLE_MAP.get(id);
    }
}
