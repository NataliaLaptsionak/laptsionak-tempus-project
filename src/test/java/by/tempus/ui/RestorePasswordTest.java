package by.tempus.ui;

import by.tempus.utils.DataGenerator;
import by.tempus.ui.login.form.LoginExpectedMessages;
import by.tempus.ui.login.form.LoginForm;
import by.tempus.ui.registration.form.RegistrationExpectedMessages;
import by.tempus.ui.restore.password.form.RestorePasswordExpectedMessages;
import by.tempus.ui.restore.password.form.RestorePasswordForm;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Testing")
@Feature("Restore Password Form")
public class RestorePasswordTest extends BaseTest {
    private LoginForm loginForm;
    private RestorePasswordForm restorePasswordForm;

    @BeforeEach
    @Step("Setup Restore Password Form")
    public void restorePasswordSetUp() {
        restorePasswordForm = new RestorePasswordForm();
        loginForm = new LoginForm();
        homePage.clickButtonLogin();
        loginForm.clickRestorePasswordLink();
    }

    @Test
    @DisplayName("Verification of redirection to restore password form form.")
    @Story("Navigation")
    @Description("Checks that clicking the 'Restore password' link navigates to the restore password form.")
    @Severity(SeverityLevel.CRITICAL)
    void RestorePasswordFormRedirectionTest() {
        assertEquals(RestorePasswordExpectedMessages.RESTORE_PASSWORD_FORM_TEXT, restorePasswordForm.getRestorePasswordFormText(), "Expected restore password form title did not match actual title after redirection.");
    }

    @Test
    @DisplayName("Verification of elements presence on the restore password form.")
    @Story("Form Structure")
    @Description("Checks that essential elements like titles, labels, and buttons are present on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyRestorePasswordFormFields() {
        assertEquals(LoginExpectedMessages.LOGIN_FORM_TITLE, restorePasswordForm.getLoginFormTitleText(),  "Login form title text is incorrect on restore password form.");
        assertEquals(LoginExpectedMessages.REGISTRATION_TAB_TITLE, restorePasswordForm.getTitleRegistrationFormText(), "Registration form title text is incorrect on restore password form.");
        assertEquals(RestorePasswordExpectedMessages.RESTORE_PASSWORD_FORM_TEXT, restorePasswordForm.getRestorePasswordFormText(), "Restore password form title text is incorrect.");
        assertEquals(RestorePasswordExpectedMessages.LABEL_EMAIL_TEXT, restorePasswordForm.getLabelRestorePassword_EmailText(), "Email field label text is incorrect on restore password form.");
        assertEquals(RestorePasswordExpectedMessages.BUTTON_RESTORE_TEXT, restorePasswordForm.getButtonSubmitRestoreText(), "Submit restore button text is incorrect on restore password form.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Email' field.")
    @Story("Form Validation")
    @Description("Tests that an error message is displayed when the 'Email' field is left empty on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyEmailField() {
        restorePasswordForm.clickButtonSubmitRestore();

        assertEquals(RestorePasswordExpectedMessages.EMPTY_EMAIL_FIELD_ERROR, restorePasswordForm.getRestorePasswordFormEmailError(), "Error message for empty email field did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the '@' symbol on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage(), "Error message for missing '@' symbol in email did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part before '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the part before the '@' symbol on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingPartBeforeAtTest() {
        RestorePasswordForm restorePasswordForm = new RestorePasswordForm();
        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartBeforeAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_BEFORE_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage(), "Error message for missing part before '@' in email did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part after '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the part after the '@' symbol on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingPartAfterAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartAfterAt();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        String expected = String.format(RestorePasswordExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_AFTER_AT, invalidEmail);
        Assertions.assertEquals(expected, restorePasswordForm.getEmailValidationMessage(), "Error message for missing part after '@' in email did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verify message for incorrect email address (e.g., '1@rtty')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for a completely malformed email address on the restore password form.")
    @Severity(SeverityLevel.NORMAL)
    public void incorrectEmailAddressTest() {
        String invalidEmail = DataGenerator.generateIncorrectEmail();
        restorePasswordForm.sendKeysEmail(invalidEmail);
        restorePasswordForm.clickButtonSubmitRestore();

        Assertions.assertEquals(RestorePasswordExpectedMessages.INCORRECT_EMAIL_ADDRESS_ERROR, loginForm.getIncorrectEmailError(), "Error message for incorrect email address did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verification of error message for unregistered email.")
    @Story("Form Validation")
    @Description("Tests that an error message is displayed when attempting to restore password for an unregistered email address.")
    @Severity(SeverityLevel.NORMAL)
    public void UnregisteredEmailTest() {
        restorePasswordForm.sendKeysEmail(DataGenerator.generateValidEmail());
        restorePasswordForm.clickButtonSubmitRestore();

        Assertions.assertEquals(RestorePasswordExpectedMessages.UNREGISTERED_EMAIL_MESSAGE, restorePasswordForm.getRestorePasswordUnregisteredEmailError(), "Error message for unregistered email did not match expected on restore password form.");
    }

    @Test
    @DisplayName("Verification of redirection to registration form.")
    @Story("Navigation")
    @Description("Checks that there is a link to navigate to the registration form from the restore password screen, and verifies the title of the registration form.")
    @Severity(SeverityLevel.TRIVIAL)
    public void RegistrationFormRedirectionTest() {
        restorePasswordForm.clickTabRegistration();

        assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, restorePasswordForm.getTitleRegistrationFormText(), "Expected registration form title did not match actual title after redirection from restore password form.");
    }
}
