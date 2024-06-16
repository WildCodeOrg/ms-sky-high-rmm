package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.UpdatePermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestUpdatePermissionDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    private String permissionName;

    private String permissionEndpoint;
}
