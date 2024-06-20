package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.getRolePermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseGetRolePermissionsDTO {
    private String message;
    private List<OperationPermissionsEntity> permissions;
}
