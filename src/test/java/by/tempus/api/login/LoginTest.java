package by.tempus.api.login;

import by.tempus.api.ExpectedMessages;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import by.tempus.utils.DataGenerator;

@Epic("API Testing")
@Feature("Authentication")
public class LoginTest {
    private LoginService loginService;
    private String validLogin;
    private String validPassword;
    private String invalidLogin;
    private String nonExistentLogin;


    @BeforeEach
    @Step("Set up test data for authentication")
    public void setup() {
        loginService = new LoginService();
        validLogin = DataGenerator.generateValidEmail();
        validPassword = DataGenerator.generateValidPassword();
        invalidLogin = DataGenerator.generateIncorrectEmail();
        nonExistentLogin = DataGenerator.generateValidEmail();
    }

    private void performLoginAndAssert(String login, String password,
                                       String expectedErrorMessage) {
        loginService.doRequest(login, password);

        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is " + 200),
                () -> assertEquals(expectedErrorMessage, loginService.getErrorMessage(), "Incorrect error message")
        );
    }

    @Test
    @DisplayName("Verify login with invalid credentials (API response). Such user doesn't exist.")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with a non-existent email returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidCredentials() {
        performLoginAndAssert(nonExistentLogin, validPassword,
                ExpectedMessages.INVALID_CREDENTIALS);
    }

    @Test
    @DisplayName("Verify login with empty email (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyEmail() {
        performLoginAndAssert("", validPassword,
                ExpectedMessages.EMPTY_EMAIL);
    }

    @Test
    @DisplayName("Verify login with empty password (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an empty password returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyPassword() {
        performLoginAndAssert(validLogin, "",
                ExpectedMessages.EMPTY_PASSWORD);
    }

    @Test
    @DisplayName("Verify login with incorrect email format (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an invalid email format returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithIncorrectEmailFormat() {
        performLoginAndAssert(invalidLogin, validPassword,
                ExpectedMessages.INVALID_EMAIL_FORMAT);
    }

    @Test
    @DisplayName("Verify login with empty email and password (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with both empty email and password returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyEmailAndPassword() {
        performLoginAndAssert("", "",
                ExpectedMessages.EMPTY_EMAIL_AND_PASSWORD);
    }
}
