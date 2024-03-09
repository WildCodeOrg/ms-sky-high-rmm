package org.skyhigh.msskyhighrmm.service;

import org.skyhigh.msskyhighrmm.model.DTO.CommonExceptionResponseDTO;
import org.skyhigh.msskyhighrmm.model.DTO.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.DeliveryResponseLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.UniversalUser;
import org.skyhigh.msskyhighrmm.model.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private static final Map<UUID, UniversalUser> UNIVERSAL_USER_MAP = new HashMap<>();

    @Override
    public UUID registerUser(DeliveryRequestRegisterUserDTO registeringUniversalUser) {
        final UUID universal_user_id = UUID.randomUUID();
        UniversalUser universalUser = new UniversalUser(universal_user_id, registeringUniversalUser.getLogin(),
                registeringUniversalUser.getPassword(), null, null);
        UNIVERSAL_USER_MAP.put(universal_user_id, universalUser);
        return universal_user_id;
    }

    @Override
    public ResponseEntity<?> checkUser(String login, String password) {
        ArrayList<UniversalUser> universalUsers = new ArrayList<>(UNIVERSAL_USER_MAP.values());
        UUID id = null;

        for (UniversalUser user : universalUsers)
            if (Objects.equals(user.getLogin(), login)) {
                id = user.getId();
                break;
            }


        if (id == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    2,
                    "Ошибка авторизации",
                    400,
                    "Данного пользователя не существует"
            ), HttpStatus.BAD_REQUEST);
        }

        return password.equals(UNIVERSAL_USER_MAP.get(id).getPassword())
                ? new ResponseEntity<>(new DeliveryResponseLoginUserDTO(login, id,
                "Авторизация пользователя прошла успешно."), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                3,
                "Ошибка авторизации",
                400,
                "Неправильный пароль"
        ), HttpStatus.BAD_REQUEST);
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
