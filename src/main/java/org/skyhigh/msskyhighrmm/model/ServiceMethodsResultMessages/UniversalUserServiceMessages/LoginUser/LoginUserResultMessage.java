package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class LoginUserResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    UUID logonUserId;

    @Override
    public String toString() {
        return "globalMessage: " +  this.globalMessage + ", globalOperationCode: " + this.globalOperationCode +
                ", logonUserId: " + this.logonUserId;
    }
}
