package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOS.updateUserById.exceptionDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String code;
    private String message;
}
