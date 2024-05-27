package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.removeRoleFromUserListDTOs;

import lombok.*;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList.RemoveRoleFromUserListResultMessageListElement;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseRemoveRoleFromUserListPartlySuccessDTO {
    private String message;

    private List<RemoveRoleFromUserListResultMessageListElement> resultMessagesPerUsers;
}
