package org.skyhigh.msskyhighrmm.model.BusinessObjects.Permissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfOperationPermissions {
    private int itemCount;
    private int paginationItemCount;
    private int pageNumber;
    private List<OperationPermissionsEntity> permissions;
}
