package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private String firstName;

    private String secondName;

    private int age;

    UserInfo(Object userInfoStringParameter, UserInfoTypes parameterType) {
        switch (parameterType) {
            case FIRST_NAME -> this.firstName = (String) userInfoStringParameter;
            case SECOND_NAME -> this.secondName = (String) userInfoStringParameter;
            case AGE -> this.age = (int) userInfoStringParameter;
        }
    }

    UserInfo(Map<UserInfoTypes, Object> userInfoParametersWithMarks) {
        for (Map.Entry<UserInfoTypes, Object> iterableObject : userInfoParametersWithMarks.entrySet()) {
            switch (iterableObject.getKey()) {
                case FIRST_NAME -> this.firstName = (String) iterableObject.getValue();
                case SECOND_NAME -> this.secondName = (String) iterableObject.getValue();
                case AGE -> this.age = (int) iterableObject.getValue();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || (
                (firstName == null ? ((UserInfo) obj).getFirstName() == null : firstName.equals(((UserInfo) obj).firstName))
                || (secondName == null ? ((UserInfo) obj).getSecondName() == null : secondName.equals(((UserInfo) obj).secondName))
                || age == ((UserInfo) obj).getAge());
    }
}



