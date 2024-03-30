package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skyhigh.msskyhighrmm.controller.RMMController;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
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

        /*UserInfo userInfo = null;
        if (user.getUser_info() != null) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                userInfo = mapper.readValue(user.getUser_info(), UserInfo.class);
            } catch (JsonProcessingException e) {
                log.info("JsonProcessingException occurred  while" +
                        "converting UserEntity {" + user.toString() + "} to UserBO");

                throw new RuntimeException(e);
            }
        }*/

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