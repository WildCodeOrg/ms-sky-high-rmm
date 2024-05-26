package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addRoleToUserDTOs;

import lombok.*;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestAddRoleToUserDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private UUID roleId;

    @NotEmpty
    private List<UUID> usersToAddRoleIds;
}
