package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class BlockUsersResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    private ArrayList<BlockUsersResultMessageListElement> certainBlockUsersResults;
}
