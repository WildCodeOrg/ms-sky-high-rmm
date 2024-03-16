package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Comparators;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserGroupRole;

import java.util.Comparator;

public class RoleDescriptionUserGroupRolesComparator implements Comparator<UserGroupRole> {
    @Override
    public int compare(UserGroupRole o1, UserGroupRole o2) {return o1.getDescription().compareTo(o2.getDescription());}
}
