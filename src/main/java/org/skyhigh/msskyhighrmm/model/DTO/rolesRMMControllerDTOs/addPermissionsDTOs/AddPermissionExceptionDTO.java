package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.exceptionDTOs.CommonExceptionResponseDTO;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessageListElement;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddPermissionExceptionDTO extends CommonExceptionResponseDTO {
    public AddPermissionExceptionDTO(int code, String description, int httpCode, String message, List<AddPermissionsResultMessageListElement> results) {
        super(code, description, httpCode, message);
        this.results = results;
    }
    private List<AddPermissionsResultMessageListElement> results;
}
