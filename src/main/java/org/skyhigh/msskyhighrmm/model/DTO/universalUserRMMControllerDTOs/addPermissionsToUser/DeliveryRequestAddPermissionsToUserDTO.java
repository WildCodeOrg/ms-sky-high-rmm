package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addPermissionsToUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestAddPermissionsToUserDTO {
    @NotEmpty
    private UUID userMadeRequestId;
    @NotEmpty
    private List<UUID> permissionIds;
}
