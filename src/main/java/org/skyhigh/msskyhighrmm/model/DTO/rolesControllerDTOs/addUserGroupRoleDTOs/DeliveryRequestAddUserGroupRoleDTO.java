package org.skyhigh.msskyhighrmm.model.DTO.rolesControllerDTOs.addUserGroupRoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestAddUserGroupRoleDTO {

    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private String role_name;

    private String description;
}
