package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.registerUserDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseRegisterUserDTO {
    private UUID registeredUserId;
}
