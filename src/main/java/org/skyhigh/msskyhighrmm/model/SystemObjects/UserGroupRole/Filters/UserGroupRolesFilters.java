package org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.CommonObjects.Criticality;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Converters.RoleEntityToRoleBOConverter;
import org.skyhigh.msskyhighrmm.repository.UserGroupRolesRepository;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRolesFilters {
    private String roleName;
    private String description;
    private Criticality criticality;
    private UserGroupRolesRepository rolesRepository;

    public static ArrayList<UserGroupRole> filter(String roleName,
                                                  String description,
                                                  Criticality criticality,
                                                  UserGroupRolesRepository rolesRepository)
    {
        if (roleName == null && description == null && criticality == null)
            return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(rolesRepository.findAll());

        if (criticality != null) {
            final boolean isCritical = criticality.isCritical();

            if (roleName != null && description != null) {
                return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                        rolesRepository.findByRoleNameANDDescriptionANDCriticality(
                            roleName,
                            description,
                            isCritical
                        )
                );
            } else if (roleName != null) {
                return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                        rolesRepository.findByRoleNameANDCriticality(
                            roleName,
                            isCritical
                        )
                );
            } else if (description != null) {
                return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                        rolesRepository.findByDescriptionANDCriticality(
                                description,
                                isCritical
                        )
                );
            } else {
                return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                        rolesRepository.findByCriticality(
                                isCritical
                        )
                );
            }
        } else if (roleName != null && description != null) {
            return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                    rolesRepository.findByRoleNameANDDescription(
                            roleName,
                            description
                    )
            );
        } else if (roleName != null) {
            return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                    rolesRepository.findByRoleName(
                            roleName
                    )
            );
        } else {
            return (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(
                    rolesRepository.findByDescription(
                            description
                    )
            );
        }
    }
}
