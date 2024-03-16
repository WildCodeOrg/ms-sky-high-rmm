package org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInfo {
    @NotEmpty
    private int page;

    @NotEmpty
    private int requestedItemCount;
}
