package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionCriticalityComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionEndpointComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionNameComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator.ValidatingEnums.SortDirection;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRoleSortParameter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationPermissionSort {
    @NotEmpty
    private String direction;

    @NotEmpty
    private String sortBy;

    public static void sort(
            ArrayList<OperationPermissionsEntity> operationPermissions,
            OperationPermissionSort operationPermissionSort
    ) {
        if (operationPermissions == null || operationPermissions.isEmpty())
            return;

        SortDirection sortDirection = SortDirection.valueOf(operationPermissionSort.direction);
        OperationPermissionSortParameter sortParameter = OperationPermissionSortParameter.valueOf(operationPermissionSort.sortBy);

        switch (sortDirection) {
            case ASC -> {
                if (sortParameter == OperationPermissionSortParameter.PERMISSION_NAME) {
                    operationPermissions.sort(new PermissionNameComparator());
                } else if (sortParameter == OperationPermissionSortParameter.PERMISSION_ENDPOINT) {
                    operationPermissions.sort(new PermissionEndpointComparator());
                } else if (sortParameter == OperationPermissionSortParameter.CRITICALITY) {
                    operationPermissions.sort(new PermissionCriticalityComparator());
                }
            }
            case DESC -> {
                if (sortParameter == OperationPermissionSortParameter.PERMISSION_NAME) {
                    operationPermissions.sort(new PermissionNameComparator().reversed());
                } else if (sortParameter == OperationPermissionSortParameter.PERMISSION_ENDPOINT) {
                    operationPermissions.sort(new PermissionEndpointComparator().reversed());
                } else if (sortParameter == OperationPermissionSortParameter.CRITICALITY) {
                    operationPermissions.sort(new PermissionCriticalityComparator().reversed());
                }
            }
        }
    }
}
