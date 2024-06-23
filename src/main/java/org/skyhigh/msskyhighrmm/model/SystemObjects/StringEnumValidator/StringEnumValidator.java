package org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator;

import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StringEnumValidator <T extends Enum<T>>  {
    private static final Logger log = Logger.getLogger(StringEnumValidator.class.getName());

    public enum StringEnumValidationResult {
        SUCCESS,
        FAILURE
    }

    /**
     * Non-exceptional validation of a string enum value boolean result method
     * @param enumClass Class of enum T for validationEnumValue validation
     * @param validatingEnumValue Validating string value
     * @return Boolean true if the validation is successful, false - unsuccessful
     */
    public boolean validateString(
            @NotNull Class<T> enumClass,
            @NotNull String validatingEnumValue
    ) {
        try {
            T.valueOf(enumClass, validatingEnumValue);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Non-exceptional validation of a string enum value map result method
     * @param enumClass Class of enum T for validationEnumValue validation
     * @param validatingEnumValue Validating string value
     * @return Map<StringEnumValidationResult, String> with NotNull value "Success" for
     *  key StringEnumValidationResult.Success
     *  if the validation is successful and NotNull value "Допустимые значения перечисления {%ATTRIBUTE_NAME%}: [%enumClass.getEnumConstants%],
     *  where {%ATTRIBUTE_NAME%} is a part of string to further replacement with API attribute name and
     *  %enumClass.getEnumConstants% is a part of string with format [EnumValue1, EnumValue2, ..., EnumValueN],
     *  for key StringEnumValidationResult.Failure"
     */
    public Map<StringEnumValidationResult, String> validateStringWithMessage(
            @NotNull Class<T> enumClass,
            @NotNull String validatingEnumValue
    ) {
        Map<StringEnumValidationResult, String> result = new HashMap<>();
        result.put(StringEnumValidationResult.SUCCESS, null);
        result.put(StringEnumValidationResult.FAILURE, null);

        try {
            T.valueOf(enumClass, validatingEnumValue);
            result.put(StringEnumValidationResult.SUCCESS, "Success");
        } catch (IllegalArgumentException e) {
            String enumConstantsString = Arrays.stream(enumClass.getEnumConstants()).toList()
                    .stream().map(Object::toString).collect(Collectors.joining(","));
            String failureMessage = "Допустимые значения перечисления {%ATTRIBUTE_NAME%}: ["
                    + enumConstantsString + "]";

            result.put(
                    StringEnumValidationResult.FAILURE,
                    failureMessage
            );
        } catch (Exception e) {
            result.put(
                    StringEnumValidationResult.FAILURE,
                    "Системная ошибка валидации перечисления"
            );

            log.warning("При валилации значения '" + validatingEnumValue + "' " +
                    "в соответствии с перечислением " + enumClass.getSimpleName() +
                    " произошла системная ошибка: " + e.getMessage()
            );
        }

        return result;
    }

    /**
     * Non-exceptional validation of a string enum value map result method
     * @param enumClass Class of enum T for validationEnumValue validation
     * @param attributeName The name of validating attribute
     * @param validatingEnumValue Validating attribute value
     * @return Map<StringEnumValidationResult, String> with NotNull value "Success" for
     *  key StringEnumValidationResult.Success
     *  if the validation is successful and NotNull value "Допустимые значения перечисления '%ATTRIBUTE_NAME%': [%enumClass.getEnumConstants%],
     *  where %ATTRIBUTE_NAME% is a part of string with attribute name and
     *  %enumClass.getEnumConstants% is a part of string with format [EnumValue1, EnumValue2, ..., EnumValueN],
     *  for key StringEnumValidationResult.Failure"
     */
    public Map<StringEnumValidationResult, String> validateAttributeWithMessage(
            @NotNull Class<T> enumClass,
            @NotNull String attributeName,
            @NotNull String validatingEnumValue
    ) {
        Map<StringEnumValidationResult, String> result = new HashMap<>();
        result.put(StringEnumValidationResult.SUCCESS, null);
        result.put(StringEnumValidationResult.FAILURE, null);

        try {
            T.valueOf(enumClass, validatingEnumValue);
            result.put(StringEnumValidationResult.SUCCESS, "Success");
        } catch (IllegalArgumentException e) {
            String enumConstantsString = Arrays.stream(enumClass.getEnumConstants()).toList()
                    .stream().map(Object::toString).collect(Collectors.joining(","));
            String failureMessage = "Допустимые значения перечисления '" + attributeName + "': ["
                    + enumConstantsString + "]";

            result.put(
                    StringEnumValidationResult.FAILURE,
                    failureMessage
            );
        } catch (Exception e) {
            result.put(
                    StringEnumValidationResult.FAILURE,
                    "Системная ошибка валидации перечисления"
            );

            log.warning("При валилации значения '" + validatingEnumValue + "' " +
                    "в соответствии с перечислением " + enumClass.getSimpleName() +
                    " произошла системная ошибка: " + e.getMessage()
            );
        }

        return result;
    }
}
