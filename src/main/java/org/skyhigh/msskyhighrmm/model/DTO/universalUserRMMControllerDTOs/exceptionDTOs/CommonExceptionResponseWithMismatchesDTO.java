package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.exceptionDTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommonExceptionResponseWithMismatchesDTO extends CommonExceptionResponseDTO {
    List<?> mismatches;
    public CommonExceptionResponseWithMismatchesDTO(final int code, final String description, final int httpCode, final String message, final List<?> mismatches) {
        this.code = code;
        this.description = description;
        this.httpCode = httpCode;
        this.message = message;
        this.mismatches = mismatches;
    }
}
