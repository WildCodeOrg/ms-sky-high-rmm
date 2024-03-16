package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolesServiceImpl implements RolesService{
    private static final Map<UUID, UserGroupRole> ROLE_MAP = new HashMap<>();

    @Override
    public UUID addRole(String roleName, String description, boolean isCritical)
    {
        boolean isRoleExisting = false;

        for (UserGroupRole role : ROLE_MAP.values())
            if (Objects.equals(role.getRole_name(), roleName)) {
                isRoleExisting = true;
                break;
            }

        if (!isRoleExisting) {
            UUID generatedRoleId = UUID.randomUUID();

            while (ROLE_MAP.get(generatedRoleId) != null) {
                generatedRoleId = UUID.randomUUID();
            }

            UserGroupRole role = new UserGroupRole(generatedRoleId, roleName, description, isCritical);
            ROLE_MAP.put(generatedRoleId, role);

            return generatedRoleId;
        } else {
            return null;
        }
    }

    @Override
    public ListOfUserGroupRoles rolesSearch(Pagination pagination, UUID roleId, UserGroupRolesFilters userGroupRolesFilters, UserGroupRolesSort userGroupRolesSort) {
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
        int firstElementOfResultListPosition = 0;
        int itemCount = temporaryAllRolesList.size();

        if (pagination != null) {
            paginationItemCount = pagination.getRequestedItemCount();
            paginationPageNumber = pagination.getPage();
            firstElementOfResultListPosition = ((paginationPageNumber - 1) * paginationItemCount);

            if (firstElementOfResultListPosition >= temporaryAllRolesList.size()
                    || paginationItemCount <= 0 || paginationPageNumber <= 0)
                return null;

            for (int i = firstElementOfResultListPosition;
                 i < firstElementOfResultListPosition + paginationItemCount; i++)
            {
                resultUserGroupRolesList.add(temporaryAllRolesList.get(i));
                if (i == temporaryAllRolesList.size() - 1) break;
            }
        } else {
            resultUserGroupRolesList = new ArrayList<>(temporaryAllRolesList);
        }

        return new ListOfUserGroupRoles(itemCount, paginationItemCount, paginationPageNumber, resultUserGroupRolesList);
    }

    private UserGroupRole getRoleById(UUID id) {
        return ROLE_MAP.get(id);
    }
}
