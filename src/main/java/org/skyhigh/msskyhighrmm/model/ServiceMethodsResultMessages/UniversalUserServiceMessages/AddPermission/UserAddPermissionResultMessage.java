package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddPermissionResultMessage {
    private int globalOperationCode;
    private String message;
    private List<UserAddPermissionResultMessageListElement> messagesPerPermissions;
}
