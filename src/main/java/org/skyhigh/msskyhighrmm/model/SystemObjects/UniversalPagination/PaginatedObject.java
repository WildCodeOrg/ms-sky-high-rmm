package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class PaginatedObject<E> {
    private ArrayList<E> resultList;

    public PaginatedObject(int paginationItemCount, int paginationPageNumber,
                                       ArrayList<E> listToPaginate) {

        ArrayList<E> result = new ArrayList<>();

        int firstElementOfResultListPosition = ((paginationPageNumber - 1) * paginationItemCount);

        if (firstElementOfResultListPosition >= listToPaginate.size()
                || paginationItemCount <= 0 || paginationPageNumber <= 0)
            this.resultList = null;
        else {
            for (int i = firstElementOfResultListPosition;
                 i < firstElementOfResultListPosition + paginationItemCount; i++) {
                result.add(listToPaginate.get(i));
                if (i == listToPaginate.size() - 1) break;
            }

            this.resultList = result;
        }
    }
}
