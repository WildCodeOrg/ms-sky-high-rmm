package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BlockUsersResultMessageListElement {
    private String message;
    private int operationCode;
}
