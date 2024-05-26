package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserRolesDTOs;

import lombok.*;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestGetUserRolesDTO {
    @NotEmpty
    private UUID userMadeRequestId;
}
