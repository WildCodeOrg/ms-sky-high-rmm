package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserPermissionDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestGetUserPermissionDTO {
    @NotEmpty
    UUID userMadeRequestId;
    @NotEmpty
    String permissionFilter;
}
