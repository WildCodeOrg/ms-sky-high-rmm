package org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Comparators;

import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.Comparator;

public class PermissionCriticalityComparator implements Comparator<OperationPermissionsEntity> {
    @Override
    public int compare(OperationPermissionsEntity o1, OperationPermissionsEntity o2) {
        if (o1.isCritical() && o2.isCritical())
            return 0;
        else if (o1.isCritical())
            return 1;
        else
            return -1;
    }
}
