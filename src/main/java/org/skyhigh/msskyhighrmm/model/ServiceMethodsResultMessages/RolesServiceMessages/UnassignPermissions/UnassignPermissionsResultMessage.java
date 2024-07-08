package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnassignPermissionsResultMessage {
    private int globalOperationCode;
    private String message;
    private List<UnassignPermissionsResultMessageListElement> messages;
}
