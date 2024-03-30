package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("age")
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
}
