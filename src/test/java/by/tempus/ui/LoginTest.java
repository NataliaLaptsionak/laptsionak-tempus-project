package by.tempus.ui;

import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.login.form.LoginExpectedMessages;
import by.tempus.ui.login.form.LoginForm;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import by.tempus.utils.DataGenerator;

@Epic("UI Testing")
@Feature("Login Form")
public class LoginTest extends BaseTest {
    LoginForm loginForm = new LoginForm();

    @BeforeEach
    @Step("Initialize Home page and Login form, navigate to login")
    public void openHomePageClickButtonLogIn() {
        new HomePage()
                .openSite()
                .clickButtonLogin();
    }

    @Test
    @DisplayName("Verification of the login form title")
    @Story("Login Form Structure")
    @Description("Checks that the login form displays the correct title.")
    @Severity(SeverityLevel.TRIVIAL)
    public void verifyLoginFormTitle() {

        Assertions.assertEquals(LoginExpectedMessages.LOGIN_FORM_TITLE, loginForm.getLoginFormTitleText());
    }

    @Test
    @DisplayName("Verification of elements presence on the login form.")
    @Story("Login Form Structure")
    @Description("Checks that all expected elements (email field, password field, login button, etc.) are present and visible on the login form.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLoginFormFields() {

        Assertions.assertEquals(LoginExpectedMessages.LOGIN_FORM_TITLE, loginForm.getLoginFormTitleText());
        Assertions.assertEquals(LoginExpectedMessages.EMAIL_FIELD_LABEL, loginForm.getLabelEmailText());
        Assertions.assertEquals(LoginExpectedMessages.PASSWORD_FIELD_LABEL, loginForm.getLabelPasswordText());
        Assertions.assertEquals(LoginExpectedMessages.RESTORE_PASSWORD_FIELD_LABEL, loginForm.getLinkRestorePasswordText());
        Assertions.assertEquals(LoginExpectedMessages.LOGIN_BUTTON_TEXT, loginForm.getButtonLoginText());
        Assertions.assertEquals(LoginExpectedMessages.REGISTRATION_TAB_TITLE, loginForm.getButtonRegistrationFormText());
    }

    @Test
    @DisplayName("Verification of error messages for empty required fields")
    @Story("Login Form Validations")
    @Description("Checks error messages when attempting to log in with empty email and password fields.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyErrorMessagesForEmptyFields() {
        loginForm.clickButtonLogin();

        Assertions.assertEquals(LoginExpectedMessages.EMPTY_EMAIL_FIELD_ERROR, loginForm.getEmptyEmailError());
        Assertions.assertEquals(LoginExpectedMessages.EMPTY_PASSWORD_FIELD_ERROR, loginForm.getEmptyPasswordError());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing '@')")
    @Story("Login Form Validations")
    @Description("Checks the error message when an email address is missing the '@' symbol.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingAt();
        loginForm.sendKeysLogin(invalidEmail);
        loginForm.sendKeysPassword(DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        String expected = String.format(LoginExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_AT, invalidEmail);
        Assertions.assertEquals(expected, loginForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Password' field.")
    @Story("Login Form Validations")
    @Description("Checks the error message when the password field is left empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyErrorMessageForEmptyPasswordField() {
        loginForm.sendKeysLogin(DataGenerator.generateValidEmail());
        loginForm.clickButtonLogin();

        Assertions.assertEquals(LoginExpectedMessages.EMPTY_PASSWORD_FIELD_ERROR, loginForm.getEmptyPasswordError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Email' field.")
    @Story("Login Form Validations")
    @Description("Checks the error message when the email field is left empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyErrorMessageForEmptyEmailField() {
        loginForm.fillLoginForm("", DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        Assertions.assertEquals(LoginExpectedMessages.EMPTY_EMAIL_FIELD_ERROR, loginForm.getEmptyEmailError());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part before '@')")
    @Story("Login Form Validations")
    @Description("Checks the error message when an email address is missing the part before the '@' symbol.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingPartBeforeAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartBeforeAt();
        loginForm.sendKeysLogin(invalidEmail);
        loginForm.sendKeysPassword(DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        String expected = String.format(LoginExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_BEFORE_AT, invalidEmail);
        Assertions.assertEquals(expected, loginForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part after '@')")
    @Description("Checks the error message when an email address is in an unrecognized format, like a simple word.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("natalialaptsionak")
    public void invalidEmailFormatMissingPartAfterAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartAfterAt();
        loginForm.sendKeysLogin(invalidEmail);
        loginForm.sendKeysPassword(DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        String expected = String.format(LoginExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_AFTER_AT, invalidEmail);
        Assertions.assertEquals(expected, loginForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email address (e.g., '1@rtty')")
    @Story("Login Form Validations")
    @Description("Checks the error message when attempting to log in with valid format but unregistered credentials.")
    @Severity(SeverityLevel.CRITICAL)
    public void incorrectEmailAddressTest() {
        String invalidEmail = DataGenerator.generateIncorrectEmail();
        loginForm.sendKeysLogin(invalidEmail);
        loginForm.sendKeysPassword(DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        Assertions.assertEquals(LoginExpectedMessages.INCORRECT_EMAIL_ADDRESS_ERROR, loginForm.getIncorrectEmailError());
    }

    @Test
    @DisplayName("Verification of error message for unregistered credentials.")
    @Story("Navigation from Login Form")
    @Description("Checks that clicking the 'Restore Password' link redirects the user to the password recovery form.")
    @Severity(SeverityLevel.MINOR)
    public void InvalidCredentialsTest() {
        loginForm.sendKeysLogin(DataGenerator.generateValidEmail());
        loginForm.sendKeysPassword(DataGenerator.generateValidPassword());
        loginForm.clickButtonLogin();

        Assertions.assertEquals(LoginExpectedMessages.UNREGISTERED_CREDENTIALS_ERROR, loginForm.getLoginCredentialsError());
    }

    @Test
    @DisplayName("Verification of redirection to password recovery form.")
    @Story("Navigation from Login Form")
    @Description("Checks that clicking the 'Registration' link redirects the user to the registration form.")
    @Severity(SeverityLevel.MINOR)
    public void RestorePasswordFormRedirectionTest() {
        loginForm.clickRestorePasswordLink();

        Assertions.assertEquals(LoginExpectedMessages.RESTORE_PASSWORD_TEXT, loginForm.getRestorePasswordFormTitleText());
    }

    @Test
    @DisplayName("Verification of redirection to registration form.")
    @Story("Navigation from Login Form")
    @Description("Checks that clicking the 'Registration' link redirects the user to the registration form.")
    @Severity(SeverityLevel.MINOR)
    public void RegistrationFormRedirectionTest() {
        loginForm.clickTabRegistration();

        Assertions.assertEquals(LoginExpectedMessages.REGISTRATION_BUTTON_TEXT, loginForm.getTitleRegistrationTab());
    }
}
