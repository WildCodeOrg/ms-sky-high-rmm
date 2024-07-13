package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.unassignPermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions.UnassignPermissionsResultMessageListElement;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseUnassignPermissionsDTO {
    private String message;
    private List<UnassignPermissionsResultMessageListElement> messagesPerPermissions;
}
