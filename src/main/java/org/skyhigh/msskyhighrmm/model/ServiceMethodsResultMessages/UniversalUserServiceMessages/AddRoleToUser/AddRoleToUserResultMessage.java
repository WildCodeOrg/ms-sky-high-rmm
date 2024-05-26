package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddRoleToUserResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    private List<AddRoleToUserResultMessageListElement> certainAddRoleToUsersResults;
}
