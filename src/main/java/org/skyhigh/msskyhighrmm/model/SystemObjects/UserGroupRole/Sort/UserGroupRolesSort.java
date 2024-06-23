package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator.ValidatingEnums.SortDirection;
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
    private String direction;

    @NotEmpty
    private String sortBy;

    public static void sort(ArrayList<UserGroupRole> userGroupRolesListToSort, UserGroupRolesSort userGroupRolesSort)
    {
        if (userGroupRolesListToSort == null || userGroupRolesListToSort.isEmpty())
            return;

        SortDirection sortDirection = SortDirection.valueOf(userGroupRolesSort.direction);
        UserGroupRoleSortParameter sortParameter = UserGroupRoleSortParameter.valueOf(userGroupRolesSort.sortBy);

        switch (sortDirection) {
            case ASC -> {
                if (sortParameter == UserGroupRoleSortParameter.ROLE_NAME) {
                    userGroupRolesListToSort.sort(new RoleNameUserGroupRolesComparator());
                } else if (sortParameter == UserGroupRoleSortParameter.ROLE_DESCRIPTION) {
                    userGroupRolesListToSort.sort(new RoleDescriptionUserGroupRolesComparator());
                } else if (sortParameter == UserGroupRoleSortParameter.ROLE_ID) {
                    userGroupRolesListToSort.sort(new RoleIdUserGroupRolesComparator());
                }
            }
            case DESC -> {
                if (sortParameter == UserGroupRoleSortParameter.ROLE_NAME) {
                    userGroupRolesListToSort.sort(new RoleNameUserGroupRolesComparator().reversed());
                } else if (sortParameter == UserGroupRoleSortParameter.ROLE_DESCRIPTION) {
                    userGroupRolesListToSort.sort(new RoleDescriptionUserGroupRolesComparator().reversed());
                } else if (sortParameter == UserGroupRoleSortParameter.ROLE_ID) {
                    userGroupRolesListToSort.sort(new RoleIdUserGroupRolesComparator().reversed());
                }
            }
        }
    }
}
