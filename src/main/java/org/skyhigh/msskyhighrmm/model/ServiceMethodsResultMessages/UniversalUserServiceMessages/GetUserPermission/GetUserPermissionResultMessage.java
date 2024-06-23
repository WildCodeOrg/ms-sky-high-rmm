package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPermissionResultMessage {
    private int globalOperationCode;
    private String message;
    private Map<String, List<OperationPermissionsEntity>> userPermissions;
}
