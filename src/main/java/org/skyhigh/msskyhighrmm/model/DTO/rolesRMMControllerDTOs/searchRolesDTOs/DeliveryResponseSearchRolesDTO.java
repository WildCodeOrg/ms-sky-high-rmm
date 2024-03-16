package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.searchRolesDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.PageInfo;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseSearchRolesDTO {
    private int foundItemAmount;
    private PageInfo pageInfo;
    private List<UserGroupRole> foundRoles;
}
