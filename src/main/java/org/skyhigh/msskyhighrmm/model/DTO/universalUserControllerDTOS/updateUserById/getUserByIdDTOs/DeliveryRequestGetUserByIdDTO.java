package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOS.updateUserById.getUserByIdDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestGetUserByIdDTO {
    @NotEmpty
    private UUID userMadeRequestId;
}
