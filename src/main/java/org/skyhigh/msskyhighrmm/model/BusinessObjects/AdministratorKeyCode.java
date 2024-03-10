package org.skyhigh.msskyhighrmm.model.BusinessObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class AdministratorKeyCode {
    private UUID id;
    private UUID user_id;
    private String key_code_value;
}
