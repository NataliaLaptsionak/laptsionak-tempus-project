package by.tempus.ui;

import by.tempus.utils.DataGenerator;
import by.tempus.ui.registration.form.RegistrationExpectedMessages;
import by.tempus.ui.registration.form.RegistrationForm;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Testing")
@Feature("Registration Form")
public class RegistrationTest extends BaseTest {
    private  RegistrationForm registrationForm;

    @BeforeEach
    @Step("Setup Registration Form")
    public void registrationSetUp() {
        registrationForm = new RegistrationForm();
        homePage.clickButtonLogin();
        registrationForm.clickTabRegistration();
    }

    @Test
    @DisplayName("Verification of the registration form title.")
    @Story("Registration Form Structure")
    @Description("Checks that the registration form displays the correct title.")
    @Severity(SeverityLevel.TRIVIAL)
    public void verifyRegistrationFormTitle() {
        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, registrationForm.getTitleRegistrationTab(),  "Registration form title did not match expected value.");
    }

    @Test
    @DisplayName("Verification of fields presence on the registration form.")
    @Story("Registration Form Structure")
    @Description("Checks that all required fields are present on the registration form.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyLRegistrationFormFields() {
        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, registrationForm.getTitleRegistrationTab(), "Registration tab title text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.FULL_NAME_FIELD_LABEL, registrationForm.getLabelFulNameFieldText(),  "Full name field label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.EMAIL_FIELD_LABEL, registrationForm.getLabelEmailFieldText(), "Email field label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.PHONE_FIELD_LABEL, registrationForm.getLabelPhoneFieldText(), "Phone field label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.PASSWORD_FIELD_LABEL, registrationForm.getLabelPasswordFieldText(), "Password field label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.REPEAT_PASSWORD_FIELD_LABEL, registrationForm.getLabelRepeatPasswordFieldText(), "Repeat password field label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.AGREEMENT_CHECKBOX_LABEL, registrationForm.getLabelAgreementCheckboxText(), "Agreement checkbox label text is incorrect.");
        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_BUTTON_TEXT, registrationForm.getButtonRegistrationText(), "Registration button text is incorrect.");
    }

    @Test
    @DisplayName("Verification of error messages for empty required fields.")
    @Story("Form Validation")
    @Description("Tests that error messages are displayed for all empty required fields upon registration attempt.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessagesForEmptyRegistrationFields() {
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_FULL_NAME_ERROR, registrationForm.getRegistrationFullNameError(), "Error message for empty Full Name field did not match expected.");
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_EMAIL_ERROR, registrationForm.getRegistrationEmailError(),"Error message for empty Email field did not match expected.");
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PHONE_ERROR, registrationForm.getRegistrationPhoneError(), "Error message for empty Phone field did not match expected.");
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PASSWORD_ERROR, registrationForm.getRegistrationPasswordError(), "Error message for empty Password field did not match expected.");
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_CHECKBOX_ERROR, registrationForm.getAgreementCheckboxError(),  "Error message for unchecked agreement checkbox did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Full name' field.")
    @Story("Form Validation")
    @Description("Verifies the error message when the 'Full name' field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyFullNameField() {
        registrationForm.fillRegistrationForm("", DataGenerator.generateValidEmail(), DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_FULL_NAME_ERROR, registrationForm.getRegistrationFullNameError(), "Error message for empty Full Name field did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Email' field.")
    @Story("Form Validation")
    @Description("Verifies the error message when the 'Email' field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyField() {
        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidRepeatPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_EMAIL_ERROR, registrationForm.getRegistrationEmailError(), "Error message for empty Email field did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Phone' field.")
    @Story("Form Validation")
    @Description("Verifies the error message when the 'Phone' field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyPhone() {
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(),"",DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PHONE_ERROR, registrationForm.getRegistrationPhoneError(), "Error message for empty Phone field did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Password' field.")
    @Story("Form Validation")
    @Description("Verifies the error message when the 'Password' field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyPassword() {
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(), DataGenerator.generateValidBelarusianPhoneNumber(),"", DataGenerator.generateValidRepeatPassword());

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PASSWORD_ERROR, registrationForm.getRegistrationPasswordError(), "Error message for empty Password field did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Repeat Password' field.")
    @Story("Form Validation")
    @Description("Verifies the error message when the 'Repeat password' field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyRepeatPassword() {
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(), DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), "");

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_REPEAT_PASSWORD_ERROR, registrationForm.getRegistrationRepeatPasswordError(), "Error message for empty Repeat Password field did not match expected.");
    }

    @Test
    @DisplayName("Verification of error message for empty 'Checkbox'.")
    @Story("Form Validation")
    @Description("Verifies the error message when the agreement checkbox is not checked.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyErrorMessageForEmptyCheckbox() {
        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateValidEmail());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidPassword());
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_CHECKBOX_ERROR, registrationForm.getAgreementCheckboxError(), "Error message for unchecked Agreement checkbox did not match expected.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part before '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the part before the '@' symbol.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingPartBeforeAtTest() {
        String invalidEmail  = DataGenerator.generateInvalidEmailMissingPartBeforeAt();
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), invalidEmail, DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_BEFORE_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage(), "Error message for missing part before '@' in email did not match expected.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part after '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the part after the '@' symbol.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingPartAfterAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartAfterAt();
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), invalidEmail, DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_AFTER_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage(), "Error message for missing part after '@' in email did not match expected.");
    }

    @Test
    @DisplayName("Verify message for incorrect email address (e.g., '1@rtty')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for a completely malformed email address.")
    @Severity(SeverityLevel.NORMAL)
    public void incorrectEmailAddressTest() {
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), DataGenerator.generateIncorrectEmail(), DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        Assertions.assertEquals(RegistrationExpectedMessages.INCORRECT_EMAIL_ERROR, registrationForm.getIncorrectEmailError(), "Error message for incorrect email address did not match expected.");
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing '@')")
    @Story("Form Validation")
    @Description("Tests that an error message is shown for an email missing the '@' symbol.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidEmailFormatMissingAtTest() {
        String invalidEmail = DataGenerator.generateInvalidEmailMissingAt();
        registrationForm.fillRegistrationForm(DataGenerator.generateValidFullName(), DataGenerator.generateInvalidEmailMissingAt(), DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage(), "Error message for missing '@' symbol in email did not match expected.");
    }
}
