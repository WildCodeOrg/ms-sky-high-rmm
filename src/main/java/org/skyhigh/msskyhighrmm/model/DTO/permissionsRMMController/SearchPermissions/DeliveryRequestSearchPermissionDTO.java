package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.SearchPermissions;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Filters.OperationPermissionFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Sort.OperationPermissionSort;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestSearchPermissionDTO {
    private PaginationInfo pagination;

    @NotNull
    private UUID userMadeRequestId;

    private UUID permissionId;

    private OperationPermissionFilters filters;

    private OperationPermissionSort Sort;
}
