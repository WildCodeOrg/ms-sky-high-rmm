package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addAdminKeyCodeDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseAddAdminKeyCodeDTO {
    private String message;
    private UUID createdAdminKeyCodeId;
}
