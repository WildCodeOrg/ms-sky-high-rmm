package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UsersToBlockInfoListElement {
    @NotEmpty
    private UUID userId;

    @NotEmpty
    private String blockReasonId;
}
