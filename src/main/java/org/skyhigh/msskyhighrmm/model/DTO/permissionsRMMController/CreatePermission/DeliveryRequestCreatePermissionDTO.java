package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.CreatePermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestCreatePermissionDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private String permissionName;

    @NotEmpty
    private String permissionEndpoint;
}
