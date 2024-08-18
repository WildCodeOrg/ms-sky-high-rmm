package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users;


import lombok.*;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversalUser{
    private UUID id;
    private int secretId;
    private UserInfo user_info;
    private String login;
    private String block_reason_id;
}
