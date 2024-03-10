package org.skyhigh.msskyhighrmm.model.BusinessObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfUniversalUser {
    private int itemCount;
    private int paginationItemCount;
    private int pageNumber;
    private List<UniversalUser> universalUsers;
}
