package org.skyhigh.msskyhighrmm.model.BusinessObjects.UsersRoles;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UserRole {
    @NotNull UUID id;
    @NotNull UUID user_id;
    @NotNull UUID role_id;
}
