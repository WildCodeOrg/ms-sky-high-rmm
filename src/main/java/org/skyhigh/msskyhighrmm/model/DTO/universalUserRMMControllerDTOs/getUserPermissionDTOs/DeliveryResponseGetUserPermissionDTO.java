package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserPermissionDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseGetUserPermissionDTO {
    private String message;
    private Map<String, List<?>> permissions;
}
