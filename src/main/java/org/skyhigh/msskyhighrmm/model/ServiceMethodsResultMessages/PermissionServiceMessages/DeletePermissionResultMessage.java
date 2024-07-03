package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePermissionResultMessage {
    private int globalOperationCode;
    private String message;
}
