package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveRoleFromUserListResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    private List<RemoveRoleFromUserListResultMessageListElement> certainResultMessages;
}
