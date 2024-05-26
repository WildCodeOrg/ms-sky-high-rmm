package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Converters;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleBOToRoleEntityConverter {
    public static UserGroupRolesEntity convert(UserGroupRole role) {
        if (role == null) return null;

        UserGroupRolesEntity resultRole = new UserGroupRolesEntity();

        if (role.getId() != null) resultRole.setId(role.getId());
        resultRole.setRoleName(role.getRoleName());
        resultRole.setDescription(role.getDescription());
        resultRole.setCritical(role.isCritical());

        return resultRole;
    }

    public static List<UserGroupRolesEntity> convertList(List<UserGroupRole> roles) {
        if (roles == null || roles.isEmpty()) return null;

        List<UserGroupRolesEntity> resultList = new ArrayList<>();

        for (UserGroupRole role : roles)
            resultList.add(convert(role));

        return resultList;
    }
}
