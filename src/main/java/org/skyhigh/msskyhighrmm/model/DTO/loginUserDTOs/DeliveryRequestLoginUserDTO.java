package org.skyhigh.msskyhighrmm.model.DTO.loginUserDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestLoginUserDTO {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
