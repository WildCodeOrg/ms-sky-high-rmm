package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddAdminKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class AddAdminKeyResultMessage {
    private String globalMessage;

    private int globalOperationCode;

    UUID adminKeyReferenceId;
}
