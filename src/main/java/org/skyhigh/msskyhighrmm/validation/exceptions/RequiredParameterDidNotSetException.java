package org.skyhigh.msskyhighrmm.validation.exceptions;

public class RequiredParameterDidNotSetException extends RequestException{
    private final String parameterName;

    public RequiredParameterDidNotSetException(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getCode() {
        return "01";
    }

    @Override
    public String getMessage() {
        return "Required parameter " + this.parameterName + " was not passed";
    }
}
