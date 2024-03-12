package org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.exceptionDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CommonExceptionResponseDTO {
    private int code;
    private String description;
    private int httpCode;
    private String message;
    /*private ArrayList<Mismatch> mismatches;

    public void addMismatch(Mismatch mismatch) {
        mismatches.add(mismatch);
    }*/
}
