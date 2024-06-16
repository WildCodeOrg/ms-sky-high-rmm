package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.SearchPermissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PageInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseSearchPermissionDTO {
    private int foundItemAmount;
    private PageInfo pageInfo;
    private List<OperationPermissionsEntity> foundPermissions;
}
