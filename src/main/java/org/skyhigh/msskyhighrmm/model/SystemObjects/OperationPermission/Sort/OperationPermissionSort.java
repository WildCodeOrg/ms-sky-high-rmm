package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionCriticalityComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionEndpointComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators.PermissionNameComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.SortDirection;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationPermissionSort {
    @NotEmpty
    private SortDirection direction;

    @NotEmpty
    private OperationPermissionSortParameter sortBy;

    public static void sort(
            ArrayList<OperationPermissionsEntity> operationPermissions,
            OperationPermissionSort operationPermissionSort
    ) {
        if (operationPermissions == null || operationPermissions.isEmpty())
            return;

        switch (operationPermissionSort.getDirection()) {
            case ASC -> {
                if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.PERMISSION_NAME) {
                    operationPermissions.sort(new PermissionNameComparator());
                } else if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.PERMISSION_ENDPOINT) {
                    operationPermissions.sort(new PermissionEndpointComparator());
                } else if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.CRITICALITY) {
                    operationPermissions.sort(new PermissionCriticalityComparator());
                }
            }
            case DESC -> {
                if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.PERMISSION_NAME) {
                    operationPermissions.sort(new PermissionNameComparator().reversed());
                } else if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.PERMISSION_ENDPOINT) {
                    operationPermissions.sort(new PermissionEndpointComparator().reversed());
                } else if (operationPermissionSort.getSortBy() == OperationPermissionSortParameter.CRITICALITY) {
                    operationPermissions.sort(new PermissionCriticalityComparator().reversed());
                }
            }
        }
    }
}
