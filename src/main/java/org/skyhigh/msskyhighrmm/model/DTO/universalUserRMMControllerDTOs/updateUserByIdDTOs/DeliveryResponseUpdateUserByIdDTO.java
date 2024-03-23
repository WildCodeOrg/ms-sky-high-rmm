package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.updateUserByIdDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseUpdateUserByIdDTO {
    private String message;
    private UniversalUser updatedUniversalUser;
}
