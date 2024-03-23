package org.skyhigh.msskyhighrmm.model.BusinessObjects.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class BlockReason {
    private String id;
    private String description;
}
