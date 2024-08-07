package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.exceptionDTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonExceptionResponseDTO {
    protected int code;
    protected String description;
    protected int httpCode;
    protected String message;
}
