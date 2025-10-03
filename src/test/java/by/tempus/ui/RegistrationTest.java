package by.tempus.ui;

import by.tempus.resources.DataGenerator;
import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.registration.form.RegistrationExpectedMessages;
import by.tempus.ui.registration.form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends BaseTest {
    RegistrationForm registrationForm = new RegistrationForm();

    @BeforeEach
    public void setupRegistrationForm() {
        new HomePage()
                .openSite()
                .clickButtonLogin();
    }

    @Test
    @DisplayName("Verification of the registration form title.")
    public void verifyRegistrationFormTitle() {
        registrationForm.clickTabRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, registrationForm.getTitleRegistrationTab());
    }

    @Test
    @DisplayName("Verification of fields presence on the registration form.")
    public void verifyLRegistrationFormFields() {
        registrationForm.clickTabRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_TAB_TITLE, registrationForm.getTitleRegistrationTab());
        Assertions.assertEquals(RegistrationExpectedMessages.FULL_NAME_FIELD_LABEL, registrationForm.getLabelFulNameFieldText());
        Assertions.assertEquals(RegistrationExpectedMessages.EMAIL_FIELD_LABEL, registrationForm.getLabelEmailFieldText());
        Assertions.assertEquals(RegistrationExpectedMessages.PHONE_FIELD_LABEL, registrationForm.getLabelPhoneFieldText());
        Assertions.assertEquals(RegistrationExpectedMessages.PASSWORD_FIELD_LABEL, registrationForm.getLabelPasswordFieldText());
        Assertions.assertEquals(RegistrationExpectedMessages.REPEAT_PASSWORD_FIELD_LABEL, registrationForm.getLabelRepeatPasswordFieldText());
        Assertions.assertEquals(RegistrationExpectedMessages.AGREEMENT_CHECKBOX_LABEL, registrationForm.getLabelAgreementCheckboxText());
        Assertions.assertEquals(RegistrationExpectedMessages.REGISTRATION_BUTTON_TEXT, registrationForm.getButtonRegistrationText());
    }

    @Test
    @DisplayName("Verification of error messages for empty required fields.")
    public void verifyErrorMessagesForEmptyRegistrationFields() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_FULL_NAME_ERROR, registrationForm.getRegistrationFullNameError());
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_EMAIL_ERROR, registrationForm.getRegistrationEmailError());
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PHONE_ERROR, registrationForm.getRegistrationPhoneError());
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PASSWORD_ERROR, registrationForm.getRegistrationPasswordError());
        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_CHECKBOX_ERROR, registrationForm.getAgreementCheckboxError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Full name' field.")
    public void verifyErrorMessageForEmptyFullNameField() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.fillRegistrationForm("", DataGenerator.generateValidEmail(), DataGenerator.generateValidBelarusianPhoneNumber(),DataGenerator.generateValidPassword(), DataGenerator.generateValidRepeatPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_FULL_NAME_ERROR, registrationForm.getRegistrationFullNameError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Email' field.")
    public void verifyErrorMessageForEmptyField() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidRepeatPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_EMAIL_ERROR, registrationForm.getRegistrationEmailError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Phone' field.")
    public void verifyErrorMessageForEmptyPhone() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateValidEmail());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidRepeatPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PHONE_ERROR, registrationForm.getRegistrationPhoneError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Password' field.")
    public void verifyErrorMessageForEmptyPassword() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateValidEmail());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidRepeatPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_PASSWORD_ERROR, registrationForm.getRegistrationPasswordError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Repeat Password' field.")
    public void verifyErrorMessageForEmptyRepeatPassword() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateValidEmail());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.clickAgreementCheckbox();
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_REPEAT_PASSWORD_ERROR, registrationForm.getRegistrationRepeatPasswordError());
    }

    @Test
    @DisplayName("Verification of error message for empty 'Checkbox'.")
    public void verifyErrorMessageForEmptyCheckbox() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateValidEmail());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidPassword());
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.EMPTY_CHECKBOX_ERROR, registrationForm.getAgreementCheckboxError());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part before '@')")
    public void invalidEmailFormatMissingPartBeforeAtTest() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartBeforeAt();
        registrationForm.sendKeysEmail(invalidEmail);
        registrationForm.clickButtonRegistration();

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_BEFORE_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing part after '@')")
    public void invalidEmailFormatMissingPartAfterAtTest() {
        registrationForm.clickTabRegistration();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingPartAfterAt();
        registrationForm.sendKeysEmail(invalidEmail);
        registrationForm.clickButtonRegistration();

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_PART_AFTER_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage());
    }

    @Test
    @DisplayName("Verify message for incorrect email address (e.g., '1@rtty')")
    public void incorrectEmailAddressTest() {
        registrationForm.clickTabRegistration();
        registrationForm.clickButtonRegistration();

        registrationForm.sendKeysFullName(DataGenerator.generateValidFullName());
        registrationForm.sendKeysEmail(DataGenerator.generateIncorrectEmail());
        registrationForm.sendKeysPhone(DataGenerator.generateValidBelarusianPhoneNumber());
        registrationForm.sendKeysPassword(DataGenerator.generateValidPassword());
        registrationForm.sendKeysRepeatPassword(DataGenerator.generateValidRepeatPassword());
        registrationForm.clickButtonRegistration();

        Assertions.assertEquals(RegistrationExpectedMessages.INCORRECT_EMAIL_ERROR, registrationForm.getIncorrectEmailError());
    }

    @Test
    @DisplayName("Verify message for incorrect email format (missing '@')")
    public void invalidEmailFormatMissingAtTest() {
        registrationForm.clickTabRegistration();

        String invalidEmail = DataGenerator.generateInvalidEmailMissingAt();
        registrationForm.sendKeysEmail(invalidEmail);
        registrationForm.clickButtonRegistration();

        String expected = String.format(RegistrationExpectedMessages.INVALID_EMAIL_FORMAT_ERROR_MISSING_AT, invalidEmail);
        Assertions.assertEquals(expected, registrationForm.getRegistrationEmailValidationMessage());
    }
}
