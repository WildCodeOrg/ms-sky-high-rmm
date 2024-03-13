package org.skyhigh.msskyhighrmm.model.DTO.rolesControllerDTOs.addUserGroupRoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseAddUserGroupRoleDTO {
    private String message;
    private UUID addedRoleId;
}
