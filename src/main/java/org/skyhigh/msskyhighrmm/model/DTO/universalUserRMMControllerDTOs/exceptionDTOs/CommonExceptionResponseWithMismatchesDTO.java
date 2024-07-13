package org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.exceptionDTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommonExceptionResponseWithMismatchesDTO<T> extends CommonExceptionResponseDTO {
    List<T> mismatches;
    public CommonExceptionResponseWithMismatchesDTO(final int code, final String description, final int httpCode, final String message, final List<T> mismatches) {
        this.code = code;
        this.description = description;
        this.httpCode = httpCode;
        this.message = message;
        this.mismatches = mismatches;
    }
}
