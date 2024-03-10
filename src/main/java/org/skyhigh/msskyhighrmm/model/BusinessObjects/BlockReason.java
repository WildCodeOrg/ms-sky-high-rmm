package org.skyhigh.msskyhighrmm.model.BusinessObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class BlockReason {
    private UUID id;
    private String description;
}
