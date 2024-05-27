package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveRoleFromUserListResultMessageListElement {
    private UUID userId;
    private String message;
    private int operationCode;
}
