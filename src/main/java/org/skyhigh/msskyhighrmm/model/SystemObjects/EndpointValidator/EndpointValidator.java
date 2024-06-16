package org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class EndpointValidator {
    private static String regexpFullMatch = "^(post:|get:|put:|delete:)\\\\([a-z-]+)\\\\(api)\\\\([a-z-_@!0-9\\\\}{]+)$";
    private static String regexpForSeparationWithHttpMethod = "^(.*:)(\\\\[a-z-]+\\\\api)(\\\\[a-z-_@!0-9\\\\}{]+)$";
    private static String regexpForSeparationWithoutHttpMethod = "^(\\\\[a-z-]+\\\\api)(\\\\[a-z-_@!0-9\\\\}{]+)$";
    private static String regexpWithoutHttpMethod = "^(\\\\[a-z-]+)\\\\(api)(\\\\[a-z-_@!0-9\\\\}{]+)$";
    private static String regexpWithoutServiceNamePrefix = "^\\\\([a-z-_@!0-9\\\\}{]+)$";
    private static String regexpForServiceNamePrefix = "^([a-z-]+\\\\api)$";

    public static ValidationResult validate(String endpoint, EndpointValidationType endpointValidationType) {
        return switch (endpointValidationType) {
            case FULL -> validateFull(endpoint);

            case WITHOUT_HTTP_METHOD -> validateWithoutHttpMethod(endpoint);

            case WITHOUT_SERVICE_NAME_PREFIX -> validateWithoutServiceNamePrefix(endpoint);

            default -> throw new IllegalStateException("Unexpected value: " + endpointValidationType);
        };
    }

    //Валидация полного эндпоинта
    private static ValidationResult validateFull(String endpoint) {
        if (Pattern.matches(regexpFullMatch, endpoint))
            return ValidationResult.SUCCESS;
        else {
            if (!Pattern.matches(regexpForSeparationWithHttpMethod, endpoint))
                return ValidationResult.FULL_UNMATCHED;

            Pattern pattern = Pattern.compile(regexpForSeparationWithHttpMethod);
            List<String> separatedEndpoint = new ArrayList<>();
            Matcher matcher = pattern.matcher(endpoint);

            while (matcher.find()){
                for (int i = 1; i < 4; i++) {
                    separatedEndpoint.add(matcher.group(i));
                }
            }

            if (!separatedEndpoint.isEmpty()) {
                boolean isIncorrectHttpMethod = true;

                for (HttpMethods httpMethod : HttpMethods.values())
                    if (separatedEndpoint.get(0).equals(httpMethod.name())) {
                        isIncorrectHttpMethod = false;
                        break;
                    }

                if (isIncorrectHttpMethod) return ValidationResult.HTTP_METHOD_UNMATCHED;

                if (!Pattern.matches(regexpForServiceNamePrefix, separatedEndpoint.get(1)))
                    return ValidationResult.SERVICE_NAME_PREFIX_UNMATCHED;

                if (!Pattern.matches(regexpWithoutServiceNamePrefix, separatedEndpoint.get(2)))
                    return ValidationResult.ENDPOINT_UNMATCHED;

                return ValidationResult.UNPROCESSABLE_ENTITY;
            } else return ValidationResult.FULL_UNMATCHED;
        }
    }

    //Валидация без http-метода
    private static ValidationResult validateWithoutHttpMethod(String endpoint) {
        if (Pattern.matches(regexpWithoutHttpMethod, endpoint))
            return ValidationResult.SUCCESS;
        else {
            Pattern pattern = Pattern.compile(regexpForSeparationWithoutHttpMethod);
            List<String> separatedEndpoint = new ArrayList<>();
            Matcher matcher = pattern.matcher(endpoint);

            while (matcher.find()){
                for (int i = 1; i < 3; i++) {
                    separatedEndpoint.add(matcher.group(i));
                }
            }

            if (!separatedEndpoint.isEmpty()) {
                if (!Pattern.matches(regexpForServiceNamePrefix, separatedEndpoint.get(0)))
                    return ValidationResult.SERVICE_NAME_PREFIX_UNMATCHED;

                if (!Pattern.matches(regexpWithoutServiceNamePrefix, separatedEndpoint.get(1)))
                    return ValidationResult.ENDPOINT_UNMATCHED;

                return ValidationResult.UNPROCESSABLE_ENTITY;
            } else return ValidationResult.FULL_UNMATCHED;
        }
    }

    //Валидация без http-метода, префикса наименования сервиса и "api"
    private static ValidationResult validateWithoutServiceNamePrefix(String endpoint) {
        if (Pattern.matches(regexpWithoutServiceNamePrefix, endpoint))
            return ValidationResult.SUCCESS;
        else
            return ValidationResult.ENDPOINT_UNMATCHED;
    }
}
