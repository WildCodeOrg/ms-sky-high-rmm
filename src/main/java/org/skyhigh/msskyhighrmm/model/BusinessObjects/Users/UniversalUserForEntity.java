package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UniversalUserForEntity {
    private UUID id;
    private String login;
    private String password;
    private String user_info;
    private String block_reason_id;
}
