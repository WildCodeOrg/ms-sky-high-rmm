package org.skyhigh.msskyhighrmm.model.SystemObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    @NotEmpty
    private int page;

    @NotEmpty
    private int requestedItemCount;
}
