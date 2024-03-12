package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.updateUsersByIdDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestUpdateUserByIdDTO {
    @NotNull
    private UUID userMadeRequestId;

    @NotNull
    private UserInfo newUserInfoAttributes;
}
