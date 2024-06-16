package org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidator.validate;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class EndpointValidatorTest {
    EndpointValidator validator = new EndpointValidator();

    @Test
    public void validateFullTest_Success(){
        String testEndpoint = "post:\\rmm-service\\api\\permissions\\{user_id}\\";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.FULL
        );

        Assertions.assertEquals(ValidationResult.SUCCESS, validationResult);
    }

    @Test
    public void validateFullTest_FullUnmatched_TrashEndpoint(){
        String testEndpoint = "hellos";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.FULL
        );

        Assertions.assertEquals(ValidationResult.FULL_UNMATCHED, validationResult);
    }

    @Test
    public void validateFullTest_FullUnmatched_IncorrectEndpoint(){
        String testEndpoint = "post:\\rmm-service\\api\\permissions\\{user_id}\\?";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.FULL
        );

        Assertions.assertEquals(ValidationResult.FULL_UNMATCHED, validationResult);
    }

    @Test
    public void validateFullTest_HttpMethodUnmatched(){
        String testEndpoint = "prst:\\rmm-service\\api\\permissions\\{user_id}";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.FULL
        );

        Assertions.assertEquals(ValidationResult.HTTP_METHOD_UNMATCHED, validationResult);
    }

    @Test
    public void validateWithoutHttpMethodTest_Success(){
        String testEndpoint = "\\rmm-service\\api\\permissions\\{user_id}\\";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_HTTP_METHOD
        );

        Assertions.assertEquals(ValidationResult.SUCCESS, validationResult);
    }

    @Test
    public void validateWithoutHttpMethodTest_FullUnmatchedTrash(){
        String testEndpoint = "hello";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_HTTP_METHOD
        );

        Assertions.assertEquals(ValidationResult.FULL_UNMATCHED, validationResult);
    }

    @Test
    public void validateWithoutHttpMethodTest_FullUnmatched(){
        String testEndpoint = "\\rmm-service\\api\\permissions\\{user_id}\\?";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_HTTP_METHOD
        );

        Assertions.assertEquals(ValidationResult.FULL_UNMATCHED, validationResult);
    }

    @Test
    public void validateWithoutServiceNamePrefix_Success(){
        String testEndpoint = "\\permissions\\{user_id}\\";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_SERVICE_NAME_PREFIX
        );

        Assertions.assertEquals(ValidationResult.SUCCESS, validationResult);
    }

    @Test
    public void validateWithoutServiceNamePrefix_EndpointUnmatchedTrash(){
        String testEndpoint = "hi";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_SERVICE_NAME_PREFIX
        );

        Assertions.assertEquals(ValidationResult.ENDPOINT_UNMATCHED, validationResult);
    }

    @Test
    public void validateWithoutServiceNamePrefix_EndpointUnmatched(){
        String testEndpoint = "permissions\\{user_id}\\";

        ValidationResult validationResult = validate(
                testEndpoint,
                EndpointValidationType.WITHOUT_SERVICE_NAME_PREFIX
        );

        Assertions.assertEquals(ValidationResult.ENDPOINT_UNMATCHED, validationResult);
    }
}
