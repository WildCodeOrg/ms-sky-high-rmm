package org.skyhigh.msskyhighrmm.model.DTO.getUserByIdDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestGetUserByIdDTO {
    @NotNull
    private UUID userMadeRequestId;
}
