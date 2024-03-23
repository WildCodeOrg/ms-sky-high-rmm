package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.searchRolesDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PageInfo;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseSearchRolesDTO {
    private int foundItemAmount;
    private PageInfo pageInfo;
    private List<UserGroupRole> foundRoles;
}
