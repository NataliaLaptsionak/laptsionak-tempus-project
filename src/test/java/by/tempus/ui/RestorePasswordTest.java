package by.tempus.ui;

import by.tempus.resources.DataGenerator;
import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.login.form.LoginExpectedMessages;
import by.tempus.ui.login.form.LoginForm;
import by.tempus.ui.registration.form.RegistrationExpectedMessages;
import by.tempus.ui.restore.password.form.RestorePasswordExpectedMessages;
import by.tempus.ui.restore.password.form.RestorePasswordForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestorePasswordTest extends BaseTest {
    LoginForm loginForm = new LoginForm();
    RestorePasswordForm restorePasswordForm = new RestorePasswordForm();

    @BeforeEach
    public void openHomePageClickButtonLogIn() {
        new HomePage()
                .openSite()
                .clickButtonLogin();
    }

    @Test
    @DisplayName("Verification of redirection to restore password form form.")
    void RestorePasswordFormRedirectionTest() {
        loginForm.clickRestorePasswordLink();

        assertEquals(RestorePasswordExpectedMessages.RESTORE_PASSWORD_FORM_TEXT, restorePasswordForm.getRestorePasswordFormText());
    }

    @Test
    @DisplayName("Verification of elements presence on the restore password form.")
    public void verifyRestorePasswordFormFields() {
        loginForm.clickRestorePasswordLink();

        assertEquals(LoginExpectedMessages.LOGIN_FORM_TITLE, restorePasswordForm.getLoginFormTitleText());
        assertEquals(LoginExpectedMessages.REGISTRATION_TAB_TITLE, restorePasswordForm.getTitleRegistrationFormText());
        assertEquals(RestorePasswordExpectedMessages.RESTORE_PASSWORD_FORM_TEXT, restorePasswordForm.getRestorePasswordFormText());
        assertEquals(RestorePasswordExpectedMessages.LABEL_EMAIL_TEXT, restorePasswordForm.getLabelRestorePassword_EmailText());
        assertEquals(RestorePasswordExpectedMessages.BUTTON_RESTORE_TEXT, restorePasswordForm.getButtonSubmitRestoreText());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Email' field.")
    public void verifyErrorMessageForEmptyEmailField() {
        loginForm.clickRestorePasswordLink();
        restorePasswordForm.clickButtonSubmitRestore();

        assertEquals(RestorePasswordExpectedMessages.EMPTY_EMAIL_FIELD_ERROR, restorePasswordForm.getRestorePasswordFormEmailError());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing '@')")
    public void invalidEmailFormatMissingAtTest() {
        loginForm.clickRestorePasswordLink();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part before '@')")
    public void invalidEmailFormatMissingPartBeforeAtTest() {
        loginForm.clickRestorePasswordLink();
        RestorePasswordForm restorePasswordForm = new RestorePasswordForm();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartBeforeAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_BEFORE_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part after '@')")
    public void invalidEmailFormatMissingPartAfterAtTest() {
        loginForm.clickRestorePasswordLink();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartAfterAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_AFTER_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email address (e.g., '1@rtty')")
    public void incorrectEmailAddressTest() {
        loginForm.clickRestorePasswordLink();

        String invalidEmail = DataGenerator.generateIncorrectEmail();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        Assertions.assertEquals(RestorePasswordExpectedMessages.INCORRECT_EMAIL_ADDRESS_ERROR, loginForm.getIncorrectEmailError());
    }

    @Test
    @DisplayName("Verification of error message for unregistered email.")
    public void UnregisteredEmailTest() {
        loginForm.clickRestorePasswordLink();

        restorePasswordForm.sendKeysEmail(DataGenerator.generateValidEmail());
        restorePasswordForm.clickButtonSubmitRestore();

        Assertions.assertEquals(RestorePasswordExpectedMessages.UNREGISTERED_EMAIL_MESSAGE, restorePasswordForm.getRestorePasswordUnregisteredEmailError());
    }

    @Test
    @DisplayName("Verification of redirection to registration form. Проверка перехода в форму регистрации")
    public void RegistrationFormRedirectionTest() {
        loginForm.clickRestorePasswordLink();
        restorePasswordForm.clickTabRegistration();

        assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, restorePasswordForm.getTitleRegistrationFormText());
    }
}
