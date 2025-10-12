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
    private String invalidEmail;
    private String unregisteredEmail;

    @BeforeEach
    @Step("Set up test data for password recovery")
    public void setUp() {
        restorePasswordService = new RestorePasswordService();
        invalidEmail = DataGenerator.generateIncorrectEmail();
        unregisteredEmail = DataGenerator.generateValidEmail();
    }

    private void performRestorePasswordAndAssert(String email, String expectedErrorMessage) {
        restorePasswordService.doRequest(email);

        assertAll(
                () -> assertEquals(200, restorePasswordService.getStatusCode(), "Expected status code is " + 200),
                () -> assertEquals(expectedErrorMessage, restorePasswordService.getErrorMessage(), "Incorrect error message")
        );
    }

    @Test
    @DisplayName("Verify password recovery with empty email (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordRecoveryEmptyEmail() {
        performRestorePasswordAndAssert("", ExpectedMessages.EMPTY_EMAIL);
    }

    @Test
    @DisplayName("Verify password recovery with invalid email format (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery with an invalid email format returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordRecoveryInvalidEmailFormat() {
        performRestorePasswordAndAssert(invalidEmail, ExpectedMessages.INVALID_EMAIL_FORMAT);
    }

    @Test
    @DisplayName("Verify restore password with unregistered email (API response).")
    @Story("Unsuccessful Password Recovery")
    @Description("Checks that attempting password recovery for an unregistered email returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPasswordRecoveryNonExistentEmail() {
        performRestorePasswordAndAssert(unregisteredEmail, ExpectedMessages.UNREGISTERED_EMAIL);
    }
}
