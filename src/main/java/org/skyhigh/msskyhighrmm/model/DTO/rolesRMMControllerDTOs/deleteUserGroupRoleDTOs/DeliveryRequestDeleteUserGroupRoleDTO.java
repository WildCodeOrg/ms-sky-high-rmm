package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.deleteUserGroupRoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestDeleteUserGroupRoleDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private UUID roleToDeleteId;
}
