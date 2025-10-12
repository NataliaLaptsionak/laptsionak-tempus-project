package by.tempus.ui.login.form;

import by.tempus.web.driver.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LoginForm {
    private static final Logger logger = LogManager.getLogger(LoginForm.class);


    public LoginForm() {
        logger.info("Инициализирован объект LoginForm.");
    }

    public String getLoginFormTitleText() {
        logger.info("Получение заголовка формы входа.");
        return WebDriver.getTextFromElement(LoginPageLocators.LOGIN_FORM_TITLE);
    }

    public String getLabelEmailText() {
        logger.info("Получение наименования поля ввода Email.");
        return WebDriver.getTextFromElement(LoginPageLocators.LABEL_EMAIL_TEXT);
    }

    public String getButtonLoginText() {
        logger.info("Получение текста кнопки 'Войти'.");
        return WebDriver.getTextFromElement(LoginPageLocators.BUTTON_LOGIN);
    }

    public String getLinkRestorePasswordText() {
        logger.info("Получение текста ссылки 'Восстановить пароль'.");
        return WebDriver.getTextFromElement(LoginPageLocators.LINK_RESTORE_PASSWORD);
    }

    public void sendKeysLogin(String login) {
        logger.info("Ввод логина '{}' в поле Email.", login);
        WebDriver.sendkeysToElement(LoginPageLocators.INPUT_LOGIN_EMAIL, login);
        logger.debug("Логин '{}' введен.", login);
    }

    public void sendKeysPassword(String password) {
        logger.info("Ввод пароля в поле Пароль.");
        WebDriver.sendkeysToElement(LoginPageLocators.INPUT_LOGIN_PASSWORD, password);
        logger.debug("Пароль введен.");
    }

    public void clickButtonLogin() {
        logger.info("Нажатие на кнопку 'Войти'.");
        WebDriver.clickElement(LoginPageLocators.BUTTON_LOGIN);
        logger.debug("Кнопка 'Войти' нажата.");
    }

    public String getLabelPasswordText() {
        logger.info("Получение наименования поля 'Пароль'.");
        return WebDriver.getTextFromElement(LoginPageLocators.LABEL_PASSWORD_TEXT);
    }

    public void clickTabRegistration() {
        logger.info("Нажатие на вкладку 'Регистрация'.");
        WebDriver.clickElement(LoginPageLocators.TAB_REGISTRATION);
        logger.debug("Вкладка 'Регистрация' нажата.");
    }

    public String getEmptyEmailError() {
        logger.info("Получение сообщения об ошибке в случае незаполненного поля Email.");
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_EMAIL_ERROR);
    }

    public String getEmptyPasswordError() {
        logger.info("Получение сообщения об ошибке  случае незаполненного поля пароля.");
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_PASSWORD_ERROR);
    }

    public String getLoginCredentialsError() {
        logger.info("Получение сообщения об ошибке учетных данных.");
        return WebDriver.getTextFromElement(LoginPageLocators.LOGIN_CREDENTIALS_ERROR);
    }

    public String getEmailValidationMessage() {
        logger.info("Получение сообщения валидации для поля Email.");
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        WebElement emailField = WebDriver.findElement(LoginPageLocators.INPUT_LOGIN_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }

    public String getIncorrectEmailError() {
        logger.info("Получение сообщения об ошибке в случае ввода некорректного Email.");
        return WebDriver.getTextFromElement(LoginPageLocators.EMPTY_EMAIL_ERROR);
    }

    public void fillLoginForm(String login, String password) {
        logger.info("Заполнение формы входа логином: '{}' и паролем (скрыто).", login);
        sendKeysLogin(login);
        sendKeysPassword(password);
        clickButtonLogin();
        logger.debug("Форма входа заполнена и кнопка 'Войти' нажата.");
    }

    public void clickRestorePasswordLink() {
        logger.info("Нажатие на ссылку 'Восстановить пароль'.");
        WebDriver.clickElement(LoginPageLocators.LINK_RESTORE_PASSWORD);
        logger.debug("Ссылка 'Восстановить пароль' нажата.");
    }

    public String getRestorePasswordFormTitleText() {
        logger.info("Получение текста заголовка 'Восстановить пароль'.");
        return WebDriver.getTextFromElement(LoginPageLocators.RESTORE_PASSWORD_FORM_TITLE_TEXT);
    }

    public String getTitleRegistrationTab() {
        logger.info("Получение заголовка вкладки 'Регистрация'.");
        return WebDriver.getTextFromElement(LoginPageLocators.TAB_REGISTRATION);
    }
}
