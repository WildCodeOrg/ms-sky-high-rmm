package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Converters;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleEntityToRoleBOConverter {
    public static UserGroupRole convert(UserGroupRolesEntity role) {
        if (role == null) return null;

        return new UserGroupRole(
                role.getId(),
                role.getRoleName(),
                role.getDescription(),
                role.isCritical()
        );
    }

    public static List<UserGroupRole> convertList(List<UserGroupRolesEntity> roles) {
        if (roles == null || roles.isEmpty()) return null;

        List<UserGroupRole> resultList = new ArrayList<>();

        for (UserGroupRolesEntity role : roles)
            resultList.add(convert(role));

        return resultList;
    }
}
