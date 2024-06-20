package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseAddPermissionDTO {
    private String message;
    private List<?> results;
}
