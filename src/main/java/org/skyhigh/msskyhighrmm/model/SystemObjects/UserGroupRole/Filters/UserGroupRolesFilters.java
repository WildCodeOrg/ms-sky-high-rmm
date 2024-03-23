package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.CommonObjects.Criticality;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;

import java.util.ArrayList;
import java.util.HashSet;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRolesFilters {
    private String roleName;
    private String description;
    private Criticality criticality;

    public static ArrayList<UserGroupRole> filter(ArrayList<UserGroupRole> userGroupRolesList,
                                                  String roleName,
                                                  String description,
                                                  Criticality criticality)
    {
        if (roleName == null && description == null && criticality == null)
            return userGroupRolesList;

        HashSet<UserGroupRole> resultSet = new HashSet<>();


        if (criticality != null) {
            final boolean isCritical = criticality.isCritical();

            if (roleName != null && description != null) {
                for (UserGroupRole role : userGroupRolesList)
                    if (role.getRole_name().equals(roleName) && role.getDescription().equals(description) &&
                            role.is_critical() == isCritical)
                        resultSet.add(role);
            } else if (roleName != null) {
                for (UserGroupRole role : userGroupRolesList)
                    if (role.getRole_name().equals(roleName) && role.is_critical() == isCritical)
                        resultSet.add(role);
            } else if (description != null) {
                for (UserGroupRole role : userGroupRolesList)
                    if (role.getDescription().equals(description) && role.is_critical() == isCritical)
                        resultSet.add(role);
            } else {
                for (UserGroupRole role : userGroupRolesList)
                    if (role.is_critical() == isCritical)
                        resultSet.add(role);
            }
        } else if (roleName != null && description != null) {
            for (UserGroupRole role : userGroupRolesList)
                if (role.getRole_name().equals(roleName) && role.getDescription().equals(description))
                    resultSet.add(role);
        } else if (roleName != null) {
            for (UserGroupRole role : userGroupRolesList)
                if (role.getRole_name().equals(roleName))
                    resultSet.add(role);
        } else {
            for (UserGroupRole role : userGroupRolesList)
                if (role.getDescription().equals(description))
                    resultSet.add(role);
        }

        return new ArrayList<>(resultSet);
    }
}
