package org.skyhigh.msskyhighrmm.model.DTO.searchUsersDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Filters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUserSearchSort.UniversalUserSort;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryRequestSearchUsersDTO {

    @NotEmpty
    private Pagination pagination;

    @NotNull
    private UUID userMadeRequestId;

    private Filters filters;

    private UniversalUserSort Sort;
}
