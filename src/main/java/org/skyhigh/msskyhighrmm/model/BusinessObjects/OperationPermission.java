package org.skyhigh.msskyhighrmm.model.BusinessObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class OperationPermission {
    private UUID id;
    private String permission_name;
    private String operation_endpoint;
    private boolean is_critical;
}
