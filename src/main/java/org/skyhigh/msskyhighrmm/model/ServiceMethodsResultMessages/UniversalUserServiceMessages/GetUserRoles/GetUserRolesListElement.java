package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRolesListElement {
    private UUID roleId;
    private String roleName;
}
