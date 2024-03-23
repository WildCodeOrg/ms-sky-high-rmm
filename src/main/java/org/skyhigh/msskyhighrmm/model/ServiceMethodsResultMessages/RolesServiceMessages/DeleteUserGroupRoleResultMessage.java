package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DeleteUserGroupRoleResultMessage {
    private String message;
    private int operationCode;
}
