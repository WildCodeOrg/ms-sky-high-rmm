package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.GetRolePermissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRolePermissionsResultMessage {
    private int globalOperationCode;
    private String message;
    private List<OperationPermissionsEntity> operationPermissions;
}
