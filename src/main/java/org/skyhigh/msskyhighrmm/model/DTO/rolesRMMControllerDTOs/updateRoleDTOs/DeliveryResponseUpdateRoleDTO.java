package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.updateRoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseUpdateRoleDTO {
    private String message;
    private UUID updatedRoleId;
}
