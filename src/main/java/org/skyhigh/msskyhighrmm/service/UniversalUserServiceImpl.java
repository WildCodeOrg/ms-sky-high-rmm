package org.skyhigh.msskyhighrmm.service;

import org.skyhigh.msskyhighrmm.model.DTO.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.UniversalUser;
import org.skyhigh.msskyhighrmm.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private static final Map<UUID, UniversalUser> UNIVERSAL_USER_MAP = new HashMap<>();

    @Override
    public UUID registerUser(DeliveryRequestRegisterUserDTO registeringUniversalUser) {
        final UUID universal_user_id = UUID.randomUUID();
        UniversalUser universalUser = new UniversalUser(universal_user_id, registeringUniversalUser.getLogin(),
                registeringUniversalUser.getPassword(), new UserInfo(), null);
        UNIVERSAL_USER_MAP.put(universal_user_id, universalUser);
        return universal_user_id;
    }

    @Override
    public void create(UniversalUser universal_user) {
        final UUID universal_user_id = UUID.randomUUID();
        universal_user.setId(universal_user_id);
        UNIVERSAL_USER_MAP.put(universal_user_id, universal_user);
    }

    @Override
    public List<UniversalUser> readAll() {
        return new ArrayList<>(UNIVERSAL_USER_MAP.values());
    }

    @Override
    public UniversalUser read(UUID id) {
        return UNIVERSAL_USER_MAP.get(id);
    }

    @Override
    public boolean update(UniversalUser universal_user, UUID id) {
        if (UNIVERSAL_USER_MAP.containsKey(id)) {
            universal_user.setId(id);
            UNIVERSAL_USER_MAP.put(id, universal_user);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return UNIVERSAL_USER_MAP.remove(id) != null;
    }
}
