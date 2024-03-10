package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUserSearchSort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.SortDirection;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUserSearchSort.SortParameters.UniversalUserSortParameter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
public class UniversalUserSort {

    @NotEmpty
    private SortDirection direction;

    @NotEmpty
    private UniversalUserSortParameter sortBy;
}
