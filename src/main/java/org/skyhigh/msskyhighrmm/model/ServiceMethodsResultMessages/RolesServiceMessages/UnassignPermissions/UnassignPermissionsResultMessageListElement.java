package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnassignPermissionsResultMessageListElement {
    private UUID permissionId;
    private int code;
    private String message;
}
