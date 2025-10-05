package by.tempus.api.login;

import by.tempus.api.ExpectedMessages;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import by.tempus.resources.DataGenerator;

@Epic("API Testing")
@Feature("Authentication")
public class LoginTest {

    private LoginService loginService;
    private String validLogin;
    private String validPassword;
    private String invalidLogin;


    @BeforeEach
    @Step("Set up test data for authentication")
    public void setup() {
        loginService = new LoginService();
        validLogin = DataGenerator.generateValidEmail();
        validPassword = DataGenerator.generateValidPassword();
        invalidLogin = DataGenerator.generateIncorrectEmail();
    }

    @Test
    @DisplayName("Verify login with invalid credentials (API response). Such user doesn't exist.")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with a non-existent email returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("BUG-1234")
    public void testLoginWithInvalidCredentials() {
        loginService.doRequest(validLogin, validPassword);
        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_CREDENTIALS, loginService.getErrorMessage(), "Incorrect error message")
        );
    }

    @Test
    @DisplayName("Verify login with empty email (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyEmail() {
        loginService.doRequest("", validPassword);
        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL, loginService.getErrorMessage(), "Incorrect error message for empty email")
        );
    }

    @Test
    @DisplayName("Verify login with empty password (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an empty password returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyPassword() {
        loginService.doRequest(validLogin, "");
        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_PASSWORD, loginService.getErrorMessage(), "Incorrect error message for empty password")
        );
    }

    @Test
    @DisplayName("Verify login with incorrect email format (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with an invalid email format returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithIncorrectEmailFormat() {
        loginService.doRequest(invalidLogin, validPassword);
        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_EMAIL_FORMAT, loginService.getErrorMessage(), "Incorrect error message for invalid email format")
        );
    }

    @Test
    @DisplayName("Verify login with empty email and password (API response).")
    @Story("Unsuccessful Login")
    @Description("Checks that attempting to log in with both empty email and password returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyEmailAndPassword() {
        loginService.doRequest("", "");
        assertAll(
                () -> assertEquals(200, loginService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL_AND_PASSWORD, loginService.getErrorMessage(), "Incorrect error message for empty email and password")
        );
    }
}

