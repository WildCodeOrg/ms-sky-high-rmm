package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.getRolePermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestGetRolePermissionsDTO {
    @NotEmpty
    private UUID userMadeRequestId;
}
