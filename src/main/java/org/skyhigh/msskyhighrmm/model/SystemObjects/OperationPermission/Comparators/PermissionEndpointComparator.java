package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators;

import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.Comparator;

public class PermissionEndpointComparator implements Comparator<OperationPermissionsEntity> {
    @Override
    public int compare(OperationPermissionsEntity o1, OperationPermissionsEntity o2) {
        return o1.getOperation_endpoint().compareTo(o2.getOperation_endpoint());
    }
}
