package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;

public class UserBOToUserEntityConverter {
    public static UniversalUserEntity convert(UniversalUser user) {
        UniversalUserEntity resultUser = new UniversalUserEntity();
        resultUser.setLogin(user.getLogin());
        resultUser.setPassword(user.getPassword());
        resultUser.setBlock_reason_id(user.getBlock_reason_id());
        resultUser.setUser_info(user.getUser_info().toString());
        return resultUser;
    }
}
