package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Comparators;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;

import java.util.Comparator;

public class RoleNameUserGroupRolesComparator implements Comparator<UserGroupRole> {
    @Override
    public int compare(UserGroupRole o1, UserGroupRole o2) {return o1.getRoleName().compareTo(o2.getRoleName());}
}
