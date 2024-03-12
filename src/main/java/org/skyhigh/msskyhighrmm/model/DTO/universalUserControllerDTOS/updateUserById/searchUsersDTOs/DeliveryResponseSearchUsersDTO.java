package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOS.updateUserById.searchUsersDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.SystemObjects.PageInfo;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryResponseSearchUsersDTO {
    private int foundItemAmount;
    private PageInfo pageInfo;
    private List<UniversalUser> universalUsers;
}
