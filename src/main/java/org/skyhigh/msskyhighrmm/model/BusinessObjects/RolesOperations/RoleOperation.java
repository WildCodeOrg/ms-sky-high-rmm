package org.skyhigh.msskyhighrmm.model.BusinessObjects.RolesOperations;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class RoleOperation {
    @NotNull UUID id;
    @NotNull UUID role_id;
    @NotNull UUID operation_id;
}
