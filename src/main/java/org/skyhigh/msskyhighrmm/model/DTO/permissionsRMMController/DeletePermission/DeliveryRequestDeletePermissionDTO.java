package org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.DeletePermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDeletePermissionDTO {
    @NotEmpty
    private UUID userMadeRequestId;
}
