package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.blockUserDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessageListElement;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseBlockUserDTO {
    private String message;
    private ArrayList<BlockUsersResultMessageListElement> certainBlockUsersResults;
}
