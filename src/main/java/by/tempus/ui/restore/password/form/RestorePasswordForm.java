package by.tempus.ui.restore.password.form;

import by.tempus.web.driver.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RestorePasswordForm {

    public String getRestorePasswordFormText() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_FORM_TEXT);
    }

    public String getButtonSubmitRestoreText() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.BUTTON_SUBMIT_RESTORE);
    }

    public void clickButtonSubmitRestore() {
        WebDriver.wait.until(ExpectedConditions.elementToBeClickable(WebDriver.findElement(RestorePasswordFormLocators.INPUT_RESTORE_PASSWORD_EMAIL)));
        WebDriver.clickElement(RestorePasswordFormLocators.BUTTON_SUBMIT_RESTORE);
    }

    public String getLabelRestorePassword_EmailText() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.LABEL_EMAIL_FIELD);
    }

    public String getLoginFormTitleText() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.LOGIN_FORM_TITLE);
    }

    public String getRestorePasswordFormEmailError() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_FORM_EMAIL_ERROR);
    }

    public String getRestorePasswordUnregisteredEmailError() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_UNREGISTERED_EMAIL_ERROR);
    }

    public String getEmailValidationMessage() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        WebElement emailField = WebDriver.findElement(RestorePasswordFormLocators.INPUT_RESTORE_PASSWORD_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }

    public void sendKeysEmail(String email) {
        WebDriver.sendkeysToElement(RestorePasswordFormLocators.INPUT_RESTORE_PASSWORD_EMAIL, email);
    }

    public String getTitleRegistrationFormText() {
        return WebDriver.getTextFromElement(RestorePasswordFormLocators.TAB_REGISTRATION);
    }

    public void clickTabRegistration() {
        WebDriver.clickElement(RestorePasswordFormLocators.TAB_REGISTRATION);
    }
}
