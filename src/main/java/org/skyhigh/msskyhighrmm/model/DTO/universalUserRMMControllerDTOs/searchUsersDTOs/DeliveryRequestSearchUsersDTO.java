package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.searchUsersDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestSearchUsersDTO {

    private PaginationInfo pagination;

    @NotNull
    private UUID userMadeRequestId;

    private UniversalUserFilters filters;

    private UniversalUserSort Sort;
}
