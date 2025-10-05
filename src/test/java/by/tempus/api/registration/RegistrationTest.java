package by.tempus.api.registration;

import by.tempus.api.ExpectedMessages;
import by.tempus.resources.DataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API Testing") // Overall epic for all API testing
@Feature("Registration")
public class RegistrationTest {
    private RegistrationService registrationService;
    private String fullName;
    private String email;
    private String phone;
    private String password;

    @BeforeEach
    @Step("Set up test data for user registration")
    public void setup() {
        registrationService = new RegistrationService();
        fullName = DataGenerator.generateValidFullName();
        email = DataGenerator.generateValidEmail();
        phone = DataGenerator.generateValidBelarusianPhoneNumber();
        password = DataGenerator.generateValidPassword();
    }

    @Test
    @DisplayName("Verify user registration with empty full name (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty full name returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyFullName() {
        registrationService.doRegistrationRequest("", email, phone, password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_FULL_NAME, registrationService.getErrorMessage(), "Incorrect error message for empty full name")
        );
    }

    @Test
    @DisplayName("Verify registration with existing email (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an already existing email returns the appropriate error.")
    public void testUserRegistrationWithExistingEmail() {
        String existingEmail = DataGenerator.generateValidEmail();
        registrationService.doRegistrationRequest(DataGenerator.generateValidFullName(), existingEmail, phone, password, password);
        Assertions.assertEquals(200, registrationService.getStatusCode());

        registrationService.doRegistrationRequest(DataGenerator.generateValidFullName(), existingEmail, DataGenerator.generateValidBelarusianPhoneNumber(), DataGenerator.generateValidPassword(), DataGenerator.generateValidPassword());

        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.REGISTRATION_WITH_EXISTING_EMAIL, registrationService.getErrorMessage(), "Incorrect error message for existing email")
        );
    }

    @Test
    @DisplayName("Verify registration with empty email (API response). ")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyEmail() {
        registrationService.doRegistrationRequest(fullName, "", phone, password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL, registrationService.getErrorMessage(), "Incorrect error message for empty email")
        );
    }

    @Test
    @DisplayName("Verify registration with incorrect phone number (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an incorrectly formatted phone number returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testIncorrectPhoneNumber() {
        registrationService.doRegistrationRequest(fullName, email, DataGenerator.generateInvalidBelarusianPhoneNumber(), password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_PHONE_NUMBER, registrationService.getErrorMessage(), "Incorrect error message for invalid phone number")
        );
    }

    @Test
    @DisplayName("Verify user registration with existing phone number (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an already existing phone number returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithExistingPhone() {
        String existingPhone = DataGenerator.generateValidBelarusianPhoneNumber();
        registrationService.doRegistrationRequest(fullName, email, existingPhone, password, password);
        Assertions.assertEquals(200, registrationService.getStatusCode());

        registrationService.doRegistrationRequest(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(), existingPhone, DataGenerator.generateValidPassword(), DataGenerator.generateValidPassword());

        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.REGISTRATION_WITH_EXISTING_PHONE, registrationService.getErrorMessage(), "Incorrect error message for existing phone number")
        );
    }

    @Test
    @DisplayName("Verify user registration with empty phone number (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty phone number returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyPhone() {
        registrationService.doRegistrationRequest(fullName, email, "", password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_PHONE, registrationService.getErrorMessage(), "Incorrect error message for empty phone number")
        );
    }

    @Test
    @DisplayName("Verify user registration with empty password (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty password returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    public void testUserRegistrationWithEmptyPassword() {
        registrationService.doRegistrationRequest(fullName, email, phone, "", "");
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_PASSWORD, registrationService.getErrorMessage(), "Incorrect error message for empty password")
        );
    }

    @Test
    @DisplayName("Verify user registration with empty repeat password (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty repeat password (if applicable, assuming this means password confirmation is missing/empty) returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyRepeatPassword() {
        registrationService.doRegistrationRequest(fullName, email, phone, password, DataGenerator.generateInvalidRepeatPassword());
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INCORRECT_REPEAT_PASSWORD, registrationService.getErrorMessage(), "Incorrect error message for empty repeat password")
        );
    }

    @Test
    @DisplayName("Verify user registration with restriction of the password length (API response).")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with a password not meeting length requirements returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithPasswordLengthRestriction() {
        registrationService.doRegistrationRequest(fullName, email, phone, DataGenerator.generateInvalidPassword(), DataGenerator.generateInvalidPassword());
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.PASSWORD_LENGTH_RESTRICTION, registrationService.getErrorMessage(), "Incorrect error message for password length restriction")
        );
    }
}
