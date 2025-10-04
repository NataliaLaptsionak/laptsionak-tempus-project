package by.tempus.api.registration;

import by.tempus.api.ExpectedMessages;
import by.tempus.resources.DataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    private RegistrationService registrationService;
    private String fullName;
    private String email;
    private String phone;
    private String password;

    @BeforeEach
    public void setup() {
        registrationService = new RegistrationService();
        fullName = DataGenerator.generateValidFullName();
        email = DataGenerator.generateValidEmail();
        phone = DataGenerator.generateValidBelarusianPhoneNumber();
        password = DataGenerator.generateValidPassword();
    }

    @Test
    @DisplayName("Verify user registration with empty full name (API response).")
    public void testUserRegistrationWithEmptyFullName() {
        registrationService.doRegistrationRequest("", email, phone, password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_FULL_NAME, registrationService.getErrorMessage(), "Incorrect error message for empty full name")
        );
    }

    @Test
    @DisplayName("Verify registration with existing email (API response).")
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
    public void testUserRegistrationWithEmptyEmail() {
        registrationService.doRegistrationRequest(fullName, "", phone, password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_EMAIL, registrationService.getErrorMessage(), "Incorrect error message for empty email")
        );
    }

    @Test
    @DisplayName("Verify registration with incorrect phone number (API response).")
    public void testIncorrectPhoneNumber() {
        registrationService.doRegistrationRequest(fullName, email, DataGenerator.generateInvalidBelarusianPhoneNumber(), password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INVALID_PHONE_NUMBER, registrationService.getErrorMessage(), "Incorrect error message for invalid phone number")
        );
    }

    @Test
    @DisplayName("Verify user registration with existing phone number (API response).")
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
    public void testUserRegistrationWithEmptyPhone() {
        registrationService.doRegistrationRequest(fullName, email, "", password, password);
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_PHONE, registrationService.getErrorMessage(), "Incorrect error message for empty phone number")
        );
    }

    @Test
    @DisplayName("Verify user registration with empty password (API response).")
    public void testUserRegistrationWithEmptyPassword() {
        registrationService.doRegistrationRequest(fullName, email, phone, "", "");
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.EMPTY_PASSWORD, registrationService.getErrorMessage(), "Incorrect error message for empty password")
        );
    }

    @Test
    @DisplayName("Verify user registration with empty repeat password (API response).")
    public void testUserRegistrationWithEmptyRepeatPassword() {
        registrationService.doRegistrationRequest(fullName, email, phone, password, DataGenerator.generateInvalidRepeatPassword());
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.INCORRECT_REPEAT_PASSWORD, registrationService.getErrorMessage(), "Incorrect error message for empty repeat password")
        );
    }

    @Test
    @DisplayName("Verify user registration with restriction of the password length (API response).")
    public void testUserRegistrationWithPasswordLengthRestriction() {
        registrationService.doRegistrationRequest(fullName, email, phone, DataGenerator.generateInvalidPassword(), DataGenerator.generateInvalidPassword());
        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is 200"),
                () -> assertEquals(ExpectedMessages.PASSWORD_LENGTH_RESTRICTION, registrationService.getErrorMessage(), "Incorrect error message for password length restriction")
        );
    }
}
