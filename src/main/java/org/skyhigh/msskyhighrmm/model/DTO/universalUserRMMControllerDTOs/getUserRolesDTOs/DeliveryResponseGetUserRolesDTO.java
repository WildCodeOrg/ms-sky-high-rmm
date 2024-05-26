package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserRolesDTOs;

import lombok.*;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles.GetUserRolesListElement;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseGetUserRolesDTO {
    private String message;

    private List<GetUserRolesListElement> userRoles;
}
