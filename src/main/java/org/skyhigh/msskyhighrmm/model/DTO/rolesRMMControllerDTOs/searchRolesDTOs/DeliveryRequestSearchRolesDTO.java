package org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.searchRolesDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestSearchRolesDTO {
    private Pagination pagination;

    @NotNull
    private UUID userMadeRequestId;

    private UUID roleId;

    private UserGroupRolesFilters filters;

    private UserGroupRolesSort Sort;
}
