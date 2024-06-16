package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators;

import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.Comparator;

public class PermissionNameComparator implements Comparator<OperationPermissionsEntity> {
    @Override
    public int compare(OperationPermissionsEntity o1, OperationPermissionsEntity o2) {
        return o1.getPermission_name().compareTo(o2.getPermission_name());
    }
}
