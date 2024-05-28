package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addBlockReasonDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestAddBlockReasonDTO {

    @NotEmpty
    private UUID userMadeRequestId;

    @NotEmpty
    private String blockReasonDescription;
}
