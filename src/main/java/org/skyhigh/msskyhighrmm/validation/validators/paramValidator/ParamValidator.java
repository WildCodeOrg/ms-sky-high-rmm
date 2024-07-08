package org.skyhigh.msskyhighrmm.validation.validators.paramValidator;

@FunctionalInterface
public interface ParamValidator {
    void validate(Object bean);
}
