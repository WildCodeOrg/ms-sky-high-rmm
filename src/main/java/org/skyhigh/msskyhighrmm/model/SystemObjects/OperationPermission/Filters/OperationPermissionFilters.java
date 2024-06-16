package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.CommonObjects.Criticality;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.repository.OperationPermissionsRepository;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationPermissionFilters {
    private String permissionName;
    private String permissionEndpoint;
    private Criticality operationCriticality;
    private OperationPermissionsRepository operationPermissionsRepository;

    public static ArrayList<OperationPermissionsEntity> filter(String permissionName,
                                                               String permissionEndpoint,
                                                               Criticality criticality,
                                                               OperationPermissionsRepository operationPermissionsRepository)
    {
        if (permissionName == null && permissionEndpoint == null && criticality == null)
            return (ArrayList<OperationPermissionsEntity>) operationPermissionsRepository.findAll();

        if (criticality != null) {
            final boolean isCritical = criticality.isCritical();

            if (permissionName != null && permissionEndpoint != null) {
                return (ArrayList<OperationPermissionsEntity>)
                        operationPermissionsRepository.findByAllArgs(
                                permissionName,
                                permissionEndpoint,
                                isCritical
                        );

            } else if (permissionName != null) {
                return (ArrayList<OperationPermissionsEntity>)
                        operationPermissionsRepository.findByNameAndCriticality(
                                permissionName,
                                isCritical
                        );
            } else if (permissionEndpoint != null) {
                return (ArrayList<OperationPermissionsEntity>)
                        operationPermissionsRepository.findByEndpointAndCriticality(
                                permissionEndpoint,
                                isCritical
                        );
            } else {
                return (ArrayList<OperationPermissionsEntity>)
                        operationPermissionsRepository.findByCriticality(
                                isCritical
                        );
            }
        } else if (permissionName != null && permissionEndpoint != null) {
            return (ArrayList<OperationPermissionsEntity>)
                    operationPermissionsRepository.findByNameAndEndpoint(
                            permissionName,
                            permissionEndpoint
                    );
        } else if (permissionName != null) {
            return (ArrayList<OperationPermissionsEntity>)
                    operationPermissionsRepository.findByPermissionName(
                            permissionName
                    );
        } else {
            return (ArrayList<OperationPermissionsEntity>)
                    operationPermissionsRepository.findByEndpoint(
                            permissionEndpoint
                    );
        }
    }
}
