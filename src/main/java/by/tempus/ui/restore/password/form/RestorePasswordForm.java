package by.tempus.ui.restore.password.form;

import by.tempus.web.driver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RestorePasswordForm {
    private static final Logger logger = LogManager.getLogger(RestorePasswordForm.class);

    public RestorePasswordForm() {
        logger.info("Инициализирован объект RestorePasswordForm.");
    }

    public String getRestorePasswordFormText() {
        logger.info("Получение текста заголовка формы восстановления пароля.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_FORM_TEXT);
    }

    public String getButtonSubmitRestoreText() {
        logger.info("Получение текста кнопки 'Восстановить'.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.BUTTON_SUBMIT_RESTORE);
    }

    public void clickButtonSubmitRestore() {
        logger.info("Нажатие на кнопку 'Восстановить'.");
        Browser.wait.until(ExpectedConditions.elementToBeClickable(Browser.findElement(RestorePasswordFormLocators.BUTTON_SUBMIT_RESTORE)));
        Browser.clickElement(RestorePasswordFormLocators.BUTTON_SUBMIT_RESTORE);
    }

    public String getLabelRestorePassword_EmailText() {
        logger.info("Получение наименования поля Email.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.LABEL_EMAIL_FIELD);
    }

    public String getLoginFormTitleText() {
        logger.info("Получение заголовка формы входа.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.LOGIN_FORM_TITLE);
    }

    public String getRestorePasswordFormEmailError() {
        logger.info("Получение сообщения об ошибке восстановления пароля.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_FORM_EMAIL_ERROR);
    }

    public String getRestorePasswordUnregisteredEmailError() {
        logger.info("Получение сообщения об ошибке в случае пустого Email при восстановлении пароля.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.RESTORE_PASSWORD_UNREGISTERED_EMAIL_ERROR);
    }

    public String getEmailValidationMessage() {
        logger.info("Получение сообщения валидации для поля Email.");
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        WebElement emailField = Browser.findElement(RestorePasswordFormLocators.INPUT_RESTORE_PASSWORD_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }

    public void sendKeysEmail(String email) {
        logger.info("Ввод Email: '{}' для восстановления пароля.", email);
        Browser.sendKeysToElement(RestorePasswordFormLocators.INPUT_RESTORE_PASSWORD_EMAIL, email);
    }

    public String getTitleRegistrationFormText() {
        logger.info("Получение текста ссылки 'К форме регистрации'.");
        return Browser.getTextFromElement(RestorePasswordFormLocators.TAB_REGISTRATION);
    }

    public void clickTabRegistration() {
        logger.info("Нажатие на вкладку 'Регистрация'.");
        Browser.wait.until(ExpectedConditions.elementToBeClickable(Browser.findElement(RestorePasswordFormLocators.TAB_REGISTRATION)));
        Browser.clickElement(RestorePasswordFormLocators.TAB_REGISTRATION);
    }
}
