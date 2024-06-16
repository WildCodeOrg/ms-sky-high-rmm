package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UsersRolesEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UpdateRole.UpdateRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters.UserEntityToUserBOConverter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Converters.RoleEntityToRoleBOConverter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;
import org.skyhigh.msskyhighrmm.repository.RolesOperationsRepository;
import org.skyhigh.msskyhighrmm.repository.UniversalUserRepository;
import org.skyhigh.msskyhighrmm.repository.UserGroupRolesRepository;
import org.skyhigh.msskyhighrmm.repository.UsersRolesRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolesServiceImpl implements RolesService{
    private final UserGroupRolesRepository userGroupRolesRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final RolesOperationsRepository rolesOperationsRepository;
    private final UniversalUserRepository universalUserRepository;

    public RolesServiceImpl(UserGroupRolesRepository userGroupRolesRepository, UsersRolesRepository usersRolesRepository, RolesOperationsRepository rolesOperationsRepository, UniversalUserRepository universalUserRepository) {
        this.userGroupRolesRepository = userGroupRolesRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.rolesOperationsRepository = rolesOperationsRepository;
        this.universalUserRepository = universalUserRepository;
    }

    //Добавление новой роли
    @Override
    public UUID addRole(String roleName, String description)
    {
        List<UserGroupRolesEntity> rolesWithSameName = userGroupRolesRepository.findByRoleName(roleName);
        if (rolesWithSameName != null && !rolesWithSameName.isEmpty()) return null;

        return (userGroupRolesRepository
                .save(new UserGroupRolesEntity(
                        null,
                        roleName,
                        description,
                        false)
                )
        ).getId();
    }

    //Параметризированный поиск ролей
    @Override
    public ListOfUserGroupRoles rolesSearch(PaginationInfo paginationInfo, UUID roleId, UserGroupRolesFilters userGroupRolesFilters, UserGroupRolesSort userGroupRolesSort) {
        ArrayList<UserGroupRole> resultUserGroupRolesList = new ArrayList<>();

        if (roleId != null) {
            if (userGroupRolesRepository.existsById(roleId)) {
                resultUserGroupRolesList.add(
                        RoleEntityToRoleBOConverter.convert(userGroupRolesRepository.getReferenceById(roleId))
                );
            } else return null;
        } else if (userGroupRolesFilters != null) {
            resultUserGroupRolesList = UserGroupRolesFilters.filter(
                    userGroupRolesFilters.getRoleName(),
                    userGroupRolesFilters.getDescription(),
                    userGroupRolesFilters.getCriticality(),
                    userGroupRolesRepository
            );
        } else {
            resultUserGroupRolesList = (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(userGroupRolesRepository.findAll());
        }

        if (resultUserGroupRolesList == null) return null;

        if (userGroupRolesSort != null) {
            UserGroupRolesSort.sort(resultUserGroupRolesList, userGroupRolesSort);
        }

        int paginationItemCount = resultUserGroupRolesList.size();
        int paginationPageNumber = 1;
        int itemCount = resultUserGroupRolesList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<UserGroupRole> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, resultUserGroupRolesList);
            resultUserGroupRolesList = paginated.getResultList();
        }

        return resultUserGroupRolesList != null
            ? new ListOfUserGroupRoles(itemCount, paginationItemCount, paginationPageNumber, resultUserGroupRolesList)
            : null;
    }

    @Override
    public DeleteUserGroupRoleResultMessage deleteRole(UUID roleId) {
        if (!userGroupRolesRepository.existsById(roleId))
            return new DeleteUserGroupRoleResultMessage(
                    "Роли с идентификатором '" + roleId + "' не сущетсвует",
                    1
            );
        if (getRoleById(roleId).isCritical())
            return new DeleteUserGroupRoleResultMessage(
                    "Роль с идентификатором '" + roleId + "' является критичной",
                    2
            );

        usersRolesRepository.deleteByRoleId(roleId);
        userGroupRolesRepository.deleteById(roleId);

        return new DeleteUserGroupRoleResultMessage(
                "Роль с идентификатором '" + roleId + "' успешно удалена",
                0
        );
    }

    @Override
    public UpdateRoleResultMessage updateRole(UUID userMadeRequestId, UUID roleId, String roleName, String description) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new UpdateRoleResultMessage(
                    1,
                    "Пользователь, инициировавший операцию, не найден"
            );

        if (roleName == null && description == null)
            return new UpdateRoleResultMessage(
                    5,
                    "Должно быть заполнено хотя бы одно из полей: [roleName, description]"
            );

        List<UserGroupRolesEntity> rolesWithSameName = userGroupRolesRepository.findByRoleName(roleName);
        if (rolesWithSameName != null && !rolesWithSameName.isEmpty())
            return new UpdateRoleResultMessage(
                    2,
                    "В Системе уже существует роль с именем, аналогичным переданному в запросе"
            );

        Optional<UserGroupRolesEntity> updatingRoleOptional = userGroupRolesRepository.findById(roleId);
        if (updatingRoleOptional.isEmpty())
            return new UpdateRoleResultMessage(
                    6,
                    "Роль с указанным roleId не существует"
            );

        if (updatingRoleOptional.get().isCritical())
            return new UpdateRoleResultMessage(
                    3,
                    "Роль является критической, обновление невозможно"
            );

        if (roleName != null && roleName.isEmpty())
            return new UpdateRoleResultMessage(
                    4,
                    "Поле roleName должно быть не заполнено или иметь длину больше 0"
            );

        UserGroupRolesEntity updatingRole = updatingRoleOptional.get();
        if (roleName != null)
            updatingRole.setRoleName(roleName);
        if (description != null)
            updatingRole.setDescription(description);

        userGroupRolesRepository.updateRoleAllArgsById(
                roleId,
                updatingRole.getRoleName(),
                updatingRole.getDescription()
        );

        return new UpdateRoleResultMessage(
                0,
                "Параметры роли успешно обновлены"
        );
    }

    private UserGroupRole getRoleById(UUID id) {
        Optional<UserGroupRolesEntity> userGroupRoleOptional = userGroupRolesRepository.findById(id);
        return userGroupRoleOptional.map(RoleEntityToRoleBOConverter::convert).orElse(null);
    }
}
