package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.SortDirection;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Comparators.RoleDescriptionUserGroupRolesComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Comparators.RoleIdUserGroupRolesComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Comparators.RoleNameUserGroupRolesComparator;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class UserGroupRolesSort {
    @NotEmpty
    private SortDirection direction;

    @NotEmpty
    private UserGroupRoleSortParameter sortBy;

    public static void sort(ArrayList<UserGroupRole> userGroupRolesListToSort, UserGroupRolesSort userGroupRolesSort)
    {
        if (userGroupRolesListToSort == null || userGroupRolesListToSort.isEmpty())
            return;

        switch (userGroupRolesSort.getDirection()) {
            case ASC -> {
                if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_NAME) {
                    userGroupRolesListToSort.sort(new RoleNameUserGroupRolesComparator());
                } else if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_DESCRIPTION) {
                    userGroupRolesListToSort.sort(new RoleDescriptionUserGroupRolesComparator());
                } else if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_ID) {
                    userGroupRolesListToSort.sort(new RoleIdUserGroupRolesComparator());
                }
            }
            case DESC -> {
                if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_NAME) {
                    userGroupRolesListToSort.sort(new RoleNameUserGroupRolesComparator().reversed());
                } else if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_DESCRIPTION) {
                    userGroupRolesListToSort.sort(new RoleDescriptionUserGroupRolesComparator().reversed());
                } else if (userGroupRolesSort.getSortBy() == UserGroupRoleSortParameter.ROLE_ID) {
                    userGroupRolesListToSort.sort(new RoleIdUserGroupRolesComparator().reversed());
                }
            }
        }
    }
}
