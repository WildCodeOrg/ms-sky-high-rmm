package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserGroupRole;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RolesServiceImpl implements RolesService{
    private static final Map<UUID, UserGroupRole> ROLE_MAP = new HashMap<>();
}
