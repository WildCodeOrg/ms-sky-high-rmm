package org.skyhigh.msskyhighrmm.model.BusinessObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UserGroupRole {
    private UUID id;
    private String role_name;
    private String description;
    private boolean is_critical;
}
