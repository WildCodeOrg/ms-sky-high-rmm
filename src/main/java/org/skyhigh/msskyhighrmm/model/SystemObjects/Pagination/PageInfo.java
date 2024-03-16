package org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PageInfo {
    private int pageNumber;
    private int onPageItemCount;
}
