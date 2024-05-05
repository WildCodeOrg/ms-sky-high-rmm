package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserBOToUserEntityConverter {
    private static final Logger log = Logger.getLogger(UserEntityToUserBOConverter.class.getName());

    public static UniversalUserEntity convert(UniversalUser user) {
        if (user == null) return null;

        UniversalUserEntity resultUser = new UniversalUserEntity();

        if (user.getId() != null) resultUser.setId(user.getId());
        resultUser.setLogin(user.getLogin());
        resultUser.setPassword(user.getPassword());
        resultUser.setBlock_reason_id(user.getBlock_reason_id());

        resultUser.setUser_info(user.getUser_info());
        return resultUser;
    }

    public static List<UniversalUserEntity> convertList(List<UniversalUser> users) {
        if (users == null || users.isEmpty()) return null;

        List<UniversalUserEntity> resultList = new ArrayList<>();

        for (UniversalUser user : users)
            resultList.add(convert(user));

        return resultList;
    }
}
