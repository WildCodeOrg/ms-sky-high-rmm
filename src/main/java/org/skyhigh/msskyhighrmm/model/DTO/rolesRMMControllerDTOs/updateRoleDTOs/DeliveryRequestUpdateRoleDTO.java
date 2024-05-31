package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.updateRoleDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestUpdateRoleDTO {

    @NotEmpty
    private UUID userMadeRequestId;
    private String roleName;
    private String description;
}
