package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOS.updateUserById.loginUserDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseLoginUserDTO {
    private String login;
    private UUID id;
    private String message;
}
