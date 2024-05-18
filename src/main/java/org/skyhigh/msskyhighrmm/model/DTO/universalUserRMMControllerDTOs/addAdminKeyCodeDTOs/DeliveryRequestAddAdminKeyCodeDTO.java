package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addAdminKeyCodeDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestAddAdminKeyCodeDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private String adminKeyCode;
}
