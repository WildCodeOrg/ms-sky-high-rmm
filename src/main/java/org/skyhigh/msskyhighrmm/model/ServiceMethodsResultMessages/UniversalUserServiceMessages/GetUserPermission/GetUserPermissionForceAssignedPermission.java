package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPermissionForceAssignedPermission {
    private OperationPermissionsEntity permission;
    private String assigningDate;
}
