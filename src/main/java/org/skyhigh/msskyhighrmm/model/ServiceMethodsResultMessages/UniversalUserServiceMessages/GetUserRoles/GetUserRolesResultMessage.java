package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRolesResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    private List<GetUserRolesListElement> rolesOfUser;
}
