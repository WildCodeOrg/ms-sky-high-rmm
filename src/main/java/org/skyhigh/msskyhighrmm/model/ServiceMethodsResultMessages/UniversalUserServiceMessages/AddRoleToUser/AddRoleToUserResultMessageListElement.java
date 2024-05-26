package org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddRoleToUserResultMessageListElement {
    private UUID userId;
    private String message;
    private int operationCode;
}
