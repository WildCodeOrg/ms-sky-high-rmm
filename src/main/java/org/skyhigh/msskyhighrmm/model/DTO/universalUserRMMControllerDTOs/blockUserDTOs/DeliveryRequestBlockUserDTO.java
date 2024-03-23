package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.blockUserDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UsersToBlockInfoListElement;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestBlockUserDTO {
    @NotEmpty
    private UUID userMadeRequestId;

    private UUID userToBlockId;

    private String blockReasonId;

    private ArrayList<UsersToBlockInfoListElement> usersToBlock;
}
