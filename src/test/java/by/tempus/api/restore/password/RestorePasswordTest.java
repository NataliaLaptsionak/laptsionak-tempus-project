package by.tempus.api.restore.password;

import by.tempus.api.ExpectedMessages;
import by.tempus.utils.DataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Testing")
@Feature("Password Recovery")
public class RestorePasswordTest {
    private RestorePasswordService restorePasswordService;
    private String validEmail;
    private String invalidEmail;


    @BeforeEach
    @Step("Set up test data for password recovery")
    public void setUp() {
        restorePasswordService = new RestorePasswordService();
        validEmail = DataGenerator.generateValidEmail();
        invalidEmail = DataGenerator.generateIncorrectEmail();
    }

    @Test
    @DisplayName("Verify password recovery with empty email (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordRecoveryEmptyEmail() {
        restorePasswordService.doRequest("");
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL, restorePasswordService.getErrorMessage(), "Incorrect error message for empty email")
        );
    }

    @Test
    @DisplayName("Verify password recovery with invalid email format (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery with an invalid email format returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordRecoveryInvalidEmailFormat() {
        restorePasswordService.doRequest(invalidEmail);
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_EMAIL_FORMAT, restorePasswordService.getErrorMessage(), "Incorrect error message for invalid email format")
        );
    }

    @Test
    @DisplayName("Verify restore password with unregistered email (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery for an unregistered email returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPasswordRecoveryNonExistentEmail() {
        restorePasswordService.doRequest(validEmail);
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.UNREGISTERED_EMAIL, restorePasswordService.getErrorMessage(), "Incorrect error message for non-existent user")
        );
    }
}
