package by.tempus.ui.login.form;

import by.tempus.web.driver.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LoginForm {

    public LoginForm() {
    }

    public String getLoginFormTitleText() {
        return WebDriver.getTextFromElement(LoginPageLocators.LOGIN_FORM_TITLE);
    }

    public String getButtonRegistrationFormText() {
        return WebDriver.getTextFromElement(LoginPageLocators.BUTTON_REGISTRATION_FORM);
    }

    public void clickTabRegistration() {
        WebDriver.clickElement(LoginPageLocators.BUTTON_REGISTRATION_FORM);
    }

    public String getLabelEmailText() {
        return WebDriver.getTextFromElement(LoginPageLocators.LABEL_EMAIL_TEXT);
    }

    public String getButtonLoginText() {
        return WebDriver.getTextFromElement(LoginPageLocators.BUTTON_LOGIN);
    }

    public String getLinkRestorePasswordText() {
        return WebDriver.getTextFromElement(LoginPageLocators.LINK_RESTORE_PASSWORD);
    }

    public void sendKeysLogin(String login) {
        WebDriver.sendkeysToElement(LoginPageLocators.INPUT_LOGIN_EMAIL, login);
    }

    public void sendKeysPassword(String password) {
        WebDriver.sendkeysToElement(LoginPageLocators.INPUT_LOGIN_PASSWORD, password);
    }

    public void clickButtonLogin() {
        WebDriver.clickElement(LoginPageLocators.BUTTON_LOGIN);
    }

    public String getLabelPasswordText() {
        return WebDriver.getTextFromElement(LoginPageLocators.LABEL_PASSWORD_TEXT);
    }

    public String getEmptyEmailError() {
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_EMAIL_ERROR);
    }

    public String getEmptyPasswordError() {
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_PASSWORD_ERROR);
    }

    public String getLoginCredentialsError() {
        return WebDriver.getTextFromElement(LoginPageLocators.LOGIN_CREDENTIALS_ERROR);
    }

    public String getEmailValidationMessage() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        WebElement emailField = WebDriver.findElement(LoginPageLocators.INPUT_LOGIN_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }

    public String getIncorrectEmailError() {
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_EMAIL_ERROR);
    }

    public void fillLoginForm(String login, String password) {
        sendKeysLogin(login);
        sendKeysPassword(password);
        clickButtonLogin();
    }

    public void clickRestorePasswordLink() {
        WebDriver.clickElement(LoginPageLocators.LINK_RESTORE_PASSWORD);
    }

    public String getRestorePasswordFormTitleText() {
        return WebDriver.getTextFromElement(LoginPageLocators.RESTORE_PASSWORD_FORM_TITLE_TEXT);
    }

    public String getTitleRegistrationTab() {
        return WebDriver.getTextFromElement(LoginPageLocators.TAB_REGISTRATION);
    }
}
