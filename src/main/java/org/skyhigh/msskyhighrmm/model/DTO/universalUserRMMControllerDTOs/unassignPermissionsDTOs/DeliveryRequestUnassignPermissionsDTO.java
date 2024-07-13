package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.unassignPermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestUnassignPermissionsDTO {
    @NotEmpty
    private UUID userMadeRequestId;
    @NotEmpty
    private UUID userId;
    @NotEmpty
    private List<UUID> permissionIds;
}
