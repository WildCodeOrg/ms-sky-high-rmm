package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestAddPermissionDTO {
    @NotEmpty
    private UUID userMadeRequestId;
    @NotEmpty
    private List<UUID> permissionIds;
}
