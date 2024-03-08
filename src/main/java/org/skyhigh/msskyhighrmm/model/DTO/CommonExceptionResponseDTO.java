package org.skyhigh.msskyhighrmm.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.Mismatch;

import java.util.ArrayList;

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
