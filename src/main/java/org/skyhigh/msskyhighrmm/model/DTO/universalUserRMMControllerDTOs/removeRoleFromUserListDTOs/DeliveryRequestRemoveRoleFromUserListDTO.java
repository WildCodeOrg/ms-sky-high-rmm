package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.removeRoleFromUserListDTOs;

import lombok.*;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestRemoveRoleFromUserListDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private List<UUID> userIds;
}
