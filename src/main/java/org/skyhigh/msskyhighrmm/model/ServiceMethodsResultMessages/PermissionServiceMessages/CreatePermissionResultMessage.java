package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionResultMessage {
    private UUID permissionID;
    private String message;
    private int globalOperationCode;
}
