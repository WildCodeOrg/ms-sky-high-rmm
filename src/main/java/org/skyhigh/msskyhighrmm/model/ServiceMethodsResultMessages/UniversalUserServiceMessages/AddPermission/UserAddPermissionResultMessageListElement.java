package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddPermissionResultMessageListElement {
    private UUID permissionId;
    private int code;
    private String message;
}
