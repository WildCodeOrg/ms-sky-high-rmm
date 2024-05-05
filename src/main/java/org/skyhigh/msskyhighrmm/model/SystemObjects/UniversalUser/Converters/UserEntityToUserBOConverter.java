package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserEntityToUserBOConverter {
    private static final Logger log = Logger.getLogger(UserEntityToUserBOConverter.class.getName());

    public static UniversalUser convert(UniversalUserEntity user) {
        if (user == null) return null;

        UniversalUser resultUser = new UniversalUser();

        resultUser.setId(user.getId());
        resultUser.setLogin(user.getLogin());
        resultUser.setPassword(user.getPassword());
        resultUser.setBlock_reason_id(user.getBlock_reason_id());

        resultUser.setUser_info(user.getUser_info());
        return resultUser;
    }

    public static List<UniversalUser> convertList(List<UniversalUserEntity> userEntities) {
        if (userEntities == null || userEntities.isEmpty()) return null;

        List<UniversalUser> resultList = new ArrayList<>();

        for (UniversalUserEntity userEntity : userEntities)
            resultList.add(convert(userEntity));

        return resultList;
    }
}
