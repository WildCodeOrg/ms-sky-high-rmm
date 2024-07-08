package org.skyhigh.msskyhighrmm.validation.validators.paramValidator;

import jakarta.validation.ValidationException;
import org.skyhigh.msskyhighrmm.validation.validators.fieldValidator.FieldValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AnnotationBasedParamValidatorImpl implements ParamValidator {

    private final Map<Class<? extends Annotation>, FieldValidator> validationFunctions;
    private final Set<Class<? extends Annotation>> supportedFieldAnnotations;

    public AnnotationBasedParamValidatorImpl(Map<Class<? extends Annotation>, FieldValidator> validationFunctions) {
        this.validationFunctions = validationFunctions;
        supportedFieldAnnotations = this.validationFunctions.keySet();
    }
    @Override
    public void validate(Object param) {
        if (param == null)
            throw new ValidationException("Passed param is null");

        Class<?> clazz = param.getClass();

        if (clazz.equals(String.class) || clazz.equals(UUID.class) || clazz.equals(Integer.class))
            return;

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            supportedFieldAnnotations.stream()
                    .filter(field::isAnnotationPresent)
                    .map(validationFunctions::get)
                    .forEach(fieldValidator -> fieldValidator.validate(param, field));
        }
    }
}
