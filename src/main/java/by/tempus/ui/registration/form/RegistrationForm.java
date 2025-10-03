package by.tempus.ui.registration.form;

import by.tempus.web.driver.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class RegistrationForm {

    public RegistrationForm() {
    }

    public String getTitleRegistrationTab() {
        return WebDriver.getTextFromElement(RegistrationLocators.TAB_REGISTRATION);
    }

    public void clickTabRegistration() {
        WebDriver.clickElement(RegistrationLocators.TAB_REGISTRATION);
    }

    public String getButtonRegistrationText() {
        return WebDriver.getTextFromElement(RegistrationLocators.BUTTON_REGISTRATION);
    }

    public void clickButtonRegistration() {
        WebDriver.scrollToElement(RegistrationLocators.BUTTON_REGISTRATION);
        WebDriver.clickElement(RegistrationLocators.BUTTON_REGISTRATION);
    }

    public String getLabelFulNameFieldText() {
        return WebDriver.getTextFromElement(RegistrationLocators.LABEL_FULL_NAME_FIELD_TEXT);
    }
    public void sendKeysFullName(String fullName) {
        WebDriver.sendkeysToElement(RegistrationLocators.INPUT_REGISTRATION_FULL_NAME, fullName);
    }

    public String getLabelEmailFieldText() {
        return WebDriver.getTextFromElement(RegistrationLocators.LABEL_EMAIL_FIELD_TEXT);
    }

    public void sendKeysEmail(String email) {
        WebDriver.sendkeysToElement(RegistrationLocators.INPUT_REGISTRATION_EMAIL, email);
    }

    public String getLabelPhoneFieldText() {
        return WebDriver.getTextFromElement(RegistrationLocators.LABEL_PHONE_FIELD_TEXT);
    }

    public void sendKeysPhone(String phone) {

        WebDriver.sendkeysToElement(RegistrationLocators.INPUT_REGISTRATION_PHONE, phone);
    }

    public String getLabelPasswordFieldText() {

        return WebDriver.getTextFromElement(RegistrationLocators.LABEL_PASSWORD_FIELD_TEXT);
    }

    public void sendKeysPassword(String password) {
        WebDriver.sendkeysToElement(RegistrationLocators.INPUT_REGISTRATION_PASSWORD, password);
    }

    public String getLabelRepeatPasswordFieldText() {
        return WebDriver.getTextFromElement(RegistrationLocators.LABEL_REPEAT_PASSWORD_FIELD_TEXT);
    }

    public void sendKeysRepeatPassword(String repeatPassword) {
        WebDriver.sendkeysToElement(RegistrationLocators.INPUT_REGISTRATION_REPEAT_PASSWORD, repeatPassword);
    }

    public String getLabelAgreementCheckboxText() {

        return WebDriver.getTextFromElement(RegistrationLocators.AGREEMENT_CHECKBOX_TEXT);
    }

    public void clickAgreementCheckbox() {
        WebDriver.scrollToElement(RegistrationLocators.AGREEMENT_CHECKBOX);
        WebDriver.clickElement(RegistrationLocators.AGREEMENT_CHECKBOX);
    }

    public String getAgreementCheckboxError() {

        return WebDriver.getTextFromElement(RegistrationLocators.AGREEMENT_CHECKBOX_ERROR);
    }

    public String getRegistrationEmailError() {

        return WebDriver.getTextFromElement(RegistrationLocators.EMPTY_EMAIL_ERROR);
    }

    public String getIncorrectEmailError() {

        return WebDriver.getTextFromElement(RegistrationLocators.INCORRECT_EMAIL_ERROR);
    }

    public String getRegistrationFullNameError() {
        return WebDriver.getTextFromElement(RegistrationLocators.EMPTY_FULL_NAME_ERROR);
    }

    public String getRegistrationPasswordError() {
        return WebDriver.getTextFromElement(RegistrationLocators.EMPTY_PASSWORD_ERROR);
    }

    public String getRegistrationRepeatPasswordError() {
        return WebDriver.getTextFromElement(RegistrationLocators.REGISTRATION_REPEAT_PASSWORD_ERROR);
    }

    public String getRegistrationPhoneError() {
        return WebDriver.getTextFromElement(RegistrationLocators.EMPTY_PHONE_ERROR);
    }

    public String getRegistrationEmailValidationMessage() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        WebElement emailField = WebDriver.findElement(RegistrationLocators.INPUT_REGISTRATION_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }

    public void fillRegistrationForm(String fullName, String email, String phone, String password, String repeatPassword) {
        sendKeysFullName(fullName);
        sendKeysEmail(email);
        sendKeysPhone(phone);
        sendKeysPassword(password);
        sendKeysRepeatPassword(repeatPassword);
        clickAgreementCheckBox();
        clickButtonRegistration();
    }

    public void clickAgreementCheckBox() {
        WebDriver.clickElement(RegistrationLocators.AGREEMENT_CHECKBOX);     }
}
