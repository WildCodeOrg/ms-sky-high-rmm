package org.skyhigh.msskyhighrmm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class blockReason {
    private UUID id;
    private String description;
}
