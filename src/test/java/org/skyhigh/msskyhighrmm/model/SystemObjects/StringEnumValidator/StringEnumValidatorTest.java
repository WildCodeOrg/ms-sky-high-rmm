package org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.logging.Logger;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class StringEnumValidatorTest {
    private static final Logger log = Logger.getLogger(StringEnumValidatorTest.class.getName());

    private enum TestEnum {
        TEST1,
        TEST2
    }

    @Test
    public void validateStringTestSuccess() {
        String filterString = TestEnum.values()[0].toString();

        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        boolean result = enumValidator.validateString(
                TestEnum.class,
                filterString
        );

        Assertions.assertTrue(result);
    }

    @Test
    public void validateStringTestFailure() {
        String filterString = "UNSUCCESSFUL_TEST";

        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        boolean result = enumValidator.validateString(
                TestEnum.class,
                filterString
        );

        Assertions.assertFalse(result);
    }

    @Test
    public void validateStringWithMessageTestSuccess() {
        String filterString = TestEnum.values()[0].toString();
        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        Map<StringEnumValidator.StringEnumValidationResult, String> result = enumValidator.validateStringWithMessage(
                TestEnum.class,
                filterString
        );

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS));
        Assertions.assertEquals(
                "Success",
                result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS)
        );
        Assertions.assertNull(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));
    }

    @Test
    public void validateStringWithMessageTestFailure() {
        String filterString = "UNSUCCESSFUL_TEST";
        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        Map<StringEnumValidator.StringEnumValidationResult, String> result = enumValidator.validateStringWithMessage(
                TestEnum.class,
                filterString
        );

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS));
        Assertions.assertNotNull(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));

        //log.info(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));

        Assertions.assertEquals(
                "Допустимые значения перечисления {%ATTRIBUTE_NAME%}: [TEST1,TEST2]",
                result.get(StringEnumValidator.StringEnumValidationResult.FAILURE)
        );
    }

    @Test
    public void validateAttributeWithMessageTestSuccess() {
        String filterString = TestEnum.values()[0].toString();
        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        Map<StringEnumValidator.StringEnumValidationResult, String> result = enumValidator.validateAttributeWithMessage(
                TestEnum.class,
                "permissionFilter",
                filterString
        );

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS));
        Assertions.assertEquals(
                "Success",
                result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS)
        );
        Assertions.assertNull(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));
    }

    @Test
    public void validateAttributeWithMessageTestFailure() {
        String filterString = "UNSUCCESSFUL_TEST";
        StringEnumValidator<TestEnum> enumValidator = new StringEnumValidator<>();

        Map<StringEnumValidator.StringEnumValidationResult, String> result = enumValidator.validateAttributeWithMessage(
                TestEnum.class,
                "permissionFilter",
                filterString
        );

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.get(StringEnumValidator.StringEnumValidationResult.SUCCESS));
        Assertions.assertNotNull(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));

        //log.info(result.get(StringEnumValidator.StringEnumValidationResult.FAILURE));

        Assertions.assertEquals(
                "Допустимые значения перечисления 'permissionFilter': [TEST1,TEST2]",
                result.get(StringEnumValidator.StringEnumValidationResult.FAILURE)
        );
    }
}
