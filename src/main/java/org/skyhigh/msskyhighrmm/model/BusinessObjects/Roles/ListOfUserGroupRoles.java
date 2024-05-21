package org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfUserGroupRoles {
    private int itemCount;
    private int paginationItemCount;
    private int pageNumber;
    private List<UserGroupRole> roles;
}

