package org.skyhigh.msskyhighrmm.model.BusinessObjects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UniversalUser{

    private UUID id;
    private String login;
    private String password;
    private UserInfo user_info;
    private UUID block_reason_id;

    public String toString() {
        return "UniversalUser(id=" + this.getId() + ", login=" + this.getLogin() +
                ", password=" + this.getPassword() + ", user_data=" + this.getUser_info() +
                ", block_reason_id=" + this. block_reason_id + ")";
    }
}
