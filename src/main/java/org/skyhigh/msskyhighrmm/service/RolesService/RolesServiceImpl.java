package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserGroupRole;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class RolesServiceImpl implements RolesService{
    private static final Map<UUID, UserGroupRole> ROLE_MAP = new HashMap<>();

    @Override
    public UUID addRole(String roleName, String description, boolean isCritical)
    {
        boolean isRoleExisting = false;

        for (UserGroupRole role : ROLE_MAP.values())
            if (Objects.equals(role.getRole_name(), roleName)) {
                isRoleExisting = true;
                break;
            }

        if (!isRoleExisting) {
            UUID generatedRoleId = UUID.randomUUID();

            while (ROLE_MAP.get(generatedRoleId) != null) {
                generatedRoleId = UUID.randomUUID();
            }

            UserGroupRole role = new UserGroupRole(generatedRoleId, roleName, description, isCritical);
            ROLE_MAP.put(generatedRoleId, role);

            return generatedRoleId;
        } else {
            return null;
        }
    }

    @Override
    public UserGroupRole getRoleById(UUID id) {
        return ROLE_MAP.get(id);
    }
}
