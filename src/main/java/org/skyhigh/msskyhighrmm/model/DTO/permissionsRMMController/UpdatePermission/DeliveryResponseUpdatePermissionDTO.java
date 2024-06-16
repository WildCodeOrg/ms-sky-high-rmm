package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.UpdatePermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseUpdatePermissionDTO {
    private UUID permissionId;
    private String message;
}
