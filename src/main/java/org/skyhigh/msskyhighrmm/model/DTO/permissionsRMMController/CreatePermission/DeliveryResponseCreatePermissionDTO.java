package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.CreatePermission;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseCreatePermissionDTO {
    private String message;
    private UUID permissionId;
}
