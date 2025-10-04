package by.tempus.api.restore.password;

import by.tempus.api.ExpectedMessages;
import by.tempus.resources.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestorePasswordTest {
    private RestorePasswordService restorePasswordService;
    private String validEmail;
    private String invalidEmail;


    @BeforeEach
    public void setUp() {
        restorePasswordService = new RestorePasswordService();
        validEmail = DataGenerator.generateValidEmail();
        invalidEmail = DataGenerator.generateIncorrectEmail();
    }

    @Test
    @DisplayName("Verify password recovery with empty email (API response).")
    public void testPasswordRecoveryEmptyEmail() {
        restorePasswordService.doRequest("");
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL, restorePasswordService.getErrorMessage(), "Incorrect error message for empty email")
        );
    }

    @Test
    @DisplayName("Verify password recovery with invalid email format (API response).")
    public void testPasswordRecoveryInvalidEmailFormat() {
        restorePasswordService.doRequest(invalidEmail);
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_EMAIL_FORMAT, restorePasswordService.getErrorMessage(), "Incorrect error message for invalid email format")
        );
    }

    @Test
    @DisplayName("Verify restore password with unregistered email (API response).")
    public void testPasswordRecoveryNonExistentEmail() {
        restorePasswordService.doRequest(validEmail);
        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.UNREGISTERED_EMAIL, restorePasswordService.getErrorMessage(), "Incorrect error message for non-existent user")
        );
    }
}
