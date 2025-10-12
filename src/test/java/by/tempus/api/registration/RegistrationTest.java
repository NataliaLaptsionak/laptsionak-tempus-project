package by.tempus.api.registration;

import by.tempus.api.ExpectedMessages;
import by.tempus.utils.DataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("API Testing")
@Feature("Registration")
public class RegistrationTest {
    private RegistrationService registrationService;
    private String validFullName;
    private String validEmail;
    private String validPhone;
    private String validPassword;
    private String validPasswordRepeat;

    @BeforeEach
    public void setup() {
        registrationService = new RegistrationService();

        validFullName = DataGenerator.generateValidFullName();
        validEmail = DataGenerator.generateValidEmail();
        validPhone = DataGenerator.generateValidBelarusianPhoneNumber();
        validPassword = DataGenerator.generateValidPassword();
        validPasswordRepeat = validPassword;
    }

    private void performRegistrationAndAssert(String fullName, String email, String phone,
                                              String password, String passwordRepeat,
                                              String expectedErrorMessage) {
        registrationService.doRegistrationRequest(fullName, email, phone, password, passwordRepeat);

        assertAll(
                () -> assertEquals(200, registrationService.getStatusCode(), "Expected status code is " + 200),
                () -> assertEquals(expectedErrorMessage, registrationService.getErrorMessage(), "Incorrect error message")
        );
    }

    @Test
    @DisplayName("verify user registration with empty full name (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty full name returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyFullName() {
        performRegistrationAndAssert("", validEmail, validPhone, validPassword, validPasswordRepeat,
                ExpectedMessages.EMPTY_FULL_NAME);
    }

    @Test
    @DisplayName("verify registration with existing email (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an already existing email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithExistingEmail() {
        performRegistrationAndAssert(validFullName, validEmail, validPhone, validPassword, validPasswordRepeat,
                null);

        performRegistrationAndAssert(validFullName, validEmail, DataGenerator.generateValidBelarusianPhoneNumber(),
                DataGenerator.generateValidPassword(), DataGenerator.generateValidPassword(),
                ExpectedMessages.REGISTRATION_WITH_EXISTING_EMAIL);
    }

    @Test
    @DisplayName("verify registration with empty email (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty email returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyEmail() {
        performRegistrationAndAssert(validFullName, "", validPhone, validPassword, validPasswordRepeat,
                ExpectedMessages.EMPTY_EMAIL);
    }

    @Test
    @DisplayName("verify registration with incorrect phone number (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an incorrectly formatted phone number returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testIncorrectPhoneNumber() {
        performRegistrationAndAssert(validFullName, validEmail, DataGenerator.generateInvalidBelarusianPhoneNumber(),
                validPassword, validPasswordRepeat,
                ExpectedMessages.INVALID_PHONE_NUMBER);
    }

    @Test
    @DisplayName("verify user registration with existing phone number (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an already existing phone number returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testRegistrationWithExistingPhone() {
        String existingPhone = DataGenerator.generateValidBelarusianPhoneNumber();
        String uniqueEmailForFirstRegistration = DataGenerator.generateValidEmail();

        performRegistrationAndAssert(validFullName, uniqueEmailForFirstRegistration, existingPhone, validPassword, validPasswordRepeat,
                null);

        performRegistrationAndAssert(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(), existingPhone,
                DataGenerator.generateValidPassword(), DataGenerator.generateValidPassword(),
                ExpectedMessages.REGISTRATION_WITH_EXISTING_PHONE);
    }

    @Test
    @DisplayName("verify user registration with empty phone (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty phone returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyPhone() {
        performRegistrationAndAssert(validFullName, validEmail, "", validPassword, validPasswordRepeat,
                ExpectedMessages.EMPTY_PHONE);
    }

    @Test
    @DisplayName("verify user registration with empty password (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty password returns the appropriate error.")
    @Severity(SeverityLevel.CRITICAL)
    public void testUserRegistrationWithEmptyPassword() {
        performRegistrationAndAssert(validFullName, validEmail, validPhone, "", "",
                ExpectedMessages.EMPTY_PASSWORD);
    }

    @Test
    @DisplayName("verify user registration with empty repeat password (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with an empty repeat password (if applicable, assuming this means password confirmation is missing/empty) returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithEmptyRepeatPassword() {
        performRegistrationAndAssert(validFullName, validEmail, validPhone, validPassword, "",
                ExpectedMessages.INCORRECT_REPEAT_PASSWORD);
    }

    @Test
    @DisplayName("verify user registration with password of the password length restriction (API response)")
    @Story("Unsuccessful Registration")
    @Description("Checks that registration with a password not meeting length requirements returns the appropriate error.")
    @Severity(SeverityLevel.NORMAL)
    public void testUserRegistrationWithPasswordLengthRestriction() {
        String invalidLengthPassword = DataGenerator.generateInvalidPassword();
        performRegistrationAndAssert(validFullName, validEmail, validPhone, invalidLengthPassword, invalidLengthPassword,
                ExpectedMessages.PASSWORD_LENGTH_RESTRICTION);
    }
}
