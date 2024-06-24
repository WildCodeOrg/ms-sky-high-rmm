package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addPermissionsToUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission.UserAddPermissionResultMessageListElement;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseAddPermissionsToUserDTO {
    private String message;
    private List<UserAddPermissionResultMessageListElement> results;
}
