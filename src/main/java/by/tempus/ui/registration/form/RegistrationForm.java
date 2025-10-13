package by.tempus.ui.registration.form;

import by.tempus.web.driver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class RegistrationForm {
    private static final Logger logger = LogManager.getLogger(RegistrationForm.class);

    public RegistrationForm() {
        logger.info("Инициализирован объект RegistrationForm.");
    }

    public String getTitleRegistrationTab() {
        logger.info("Получение заголовка формы регистрации.");
        return Browser.getTextFromElement(RegistrationLocators.TAB_REGISTRATION);
    }

    public void clickTabRegistration() {
        logger.info("Нажатие на вкладку 'Регистрация'.");
        Browser.clickElement(RegistrationLocators.TAB_REGISTRATION);
        logger.debug("Вкладка 'Регистрация' нажата.");
    }

    public String getButtonRegistrationText() {
        logger.info("Получение текста кнопки 'Регистрация'.");
        return Browser.getTextFromElement(RegistrationLocators.BUTTON_REGISTRATION);
    }

    public void clickButtonRegistration() {
        logger.info("Нажатие на кнопку 'Регистрация'.");
        Browser.scrollToElement(RegistrationLocators.BUTTON_REGISTRATION);
        Browser.clickElement(RegistrationLocators.BUTTON_REGISTRATION);
    }

    public String getLabelFulNameFieldText() {
        logger.info("Получение наименования поля 'ФИО'.");
        return Browser.getTextFromElement(RegistrationLocators.LABEL_FULL_NAME_FIELD_TEXT);
    }

    public void sendKeysFullName(String fullName) {
        logger.info("Ввод ФИО: '{}'.", fullName);
        Browser.sendKeysToElement(RegistrationLocators.INPUT_REGISTRATION_FULL_NAME, fullName);
        logger.debug("Полное имя '{}' введено.", fullName);
    }

    public String getLabelEmailFieldText() {
        logger.info("Получение наименования поля Email.");
        return Browser.getTextFromElement(RegistrationLocators.LABEL_EMAIL_FIELD_TEXT);
    }

    public void sendKeysEmail(String email) {
        logger.info("Ввод Email: '{}'.", email);
        Browser.sendKeysToElement(RegistrationLocators.INPUT_REGISTRATION_EMAIL, email);
        logger.debug("Email '{}' введен.", email);
    }

    public String getLabelPhoneFieldText() {
        logger.info("Получение наименования поля 'Телефон'.");
        return Browser.getTextFromElement(RegistrationLocators.LABEL_PHONE_FIELD_TEXT);
    }

    public void sendKeysPhone(String phone) {
        logger.info("Ввод телефона: '{}'.", phone);
        Browser.sendKeysToElement(RegistrationLocators.INPUT_REGISTRATION_PHONE, phone);
        logger.debug("Телефон '{}' введен.", phone);
    }

    public String getLabelPasswordFieldText() {
        logger.info("Получение наименования поля 'Пароль'.");
        return Browser.getTextFromElement(RegistrationLocators.LABEL_PASSWORD_FIELD_TEXT);
    }

    public void sendKeysPassword(String password) {
        logger.info("Ввод пароля (скрыто) в поле 'Пароль'.");
        Browser.scrollToElement(RegistrationLocators.INPUT_REGISTRATION_PASSWORD);
        Browser.sendKeysToElement(RegistrationLocators.INPUT_REGISTRATION_PASSWORD, password);
        logger.debug("Пароль введен.");
    }

    public String getLabelRepeatPasswordFieldText() {
        logger.info("Получение наименования поля 'Повторить пароль'.");
        return Browser.getTextFromElement(RegistrationLocators.LABEL_REPEAT_PASSWORD_FIELD_TEXT);
    }

    public void sendKeysRepeatPassword(String repeatPassword) {
        logger.info("Ввод пароля в поле 'Повторить пароль'.");
        Browser.scrollToElement(RegistrationLocators.INPUT_REGISTRATION_REPEAT_PASSWORD);
        Browser.sendKeysToElement(RegistrationLocators.INPUT_REGISTRATION_REPEAT_PASSWORD, repeatPassword);
        logger.debug("Пароль в поле 'Повторить пароль' введен.");
    }

    public String getLabelAgreementCheckboxText() {
        logger.info("Получение текста чек-бокса о принятии соглашений ");
        return Browser.getTextFromElement(RegistrationLocators.AGREEMENT_CHECKBOX_TEXT);
    }

    public void clickAgreementCheckbox() {
        logger.info("Нажатие на чекбокс 'Согласие с условиями'.");
        Browser.scrollToElement(RegistrationLocators.AGREEMENT_CHECKBOX);
        Browser.clickElement(RegistrationLocators.AGREEMENT_CHECKBOX);
        logger.debug("Чекбокс 'Согласие с условиями' нажат.");
    }

    public String getAgreementCheckboxError() {
        logger.info("Получение сообщения об ошибке чекбокса 'Согласие с условиями'.");
        return Browser.getTextFromElement(RegistrationLocators.AGREEMENT_CHECKBOX_ERROR);
    }

    public String getRegistrationEmailError() {
        logger.info("Получение сообщения об ошибке в случае незаполненного поля Email.");
        return Browser.getTextFromElement(RegistrationLocators.EMPTY_EMAIL_ERROR);
    }

    public String getIncorrectEmailError() {
        logger.info("Получение сообщения об ошибке в случае некорректного Email.");
        return Browser.getTextFromElement(RegistrationLocators.INCORRECT_EMAIL_ERROR);
    }

    public String getRegistrationFullNameError() {
        logger.info("Получение сообщения об ошибке в случае пустого поля 'Полное имя'.");
        return Browser.getTextFromElement(RegistrationLocators.EMPTY_FULL_NAME_ERROR);
    }

    public String getRegistrationPasswordError() {
        logger.info("Получение сообщения об ошибке в случае пустого пароля.");
        return Browser.getTextFromElement(RegistrationLocators.EMPTY_PASSWORD_ERROR);
    }

    public String getRegistrationRepeatPasswordError() {
        logger.info("Получение сообщения об ошибке в случае повторяющегося пароля.");
        return Browser.getTextFromElement(RegistrationLocators.REGISTRATION_REPEAT_PASSWORD_ERROR);
    }

    public String getRegistrationPhoneError() {
        logger.info("Получение сообщения об ошибке в случае пустого номера телефона.");
        return Browser.getTextFromElement(RegistrationLocators.EMPTY_PHONE_ERROR);
    }

    public String getRegistrationEmailValidationMessage() {
        logger.info("Получение сообщения валидации для поля Email.");
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        WebElement emailField = Browser.findElement(RegistrationLocators.INPUT_REGISTRATION_EMAIL);
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);

    }

    public void fillRegistrationForm(String fullName, String email, String phone, String password, String repeatPassword) {
        logger.info("Заполнение формы регистрации с данными: fullName='{}', email='{}', phone='{}', password (скрыто), repeatPassword (скрыто).", fullName, email, phone);
        sendKeysFullName(fullName);
        sendKeysEmail(email);
        sendKeysPhone(phone);
        sendKeysPassword(password);
        sendKeysRepeatPassword(repeatPassword);
        clickAgreementCheckBox();
        clickButtonRegistration();
        logger.debug("Форма регистрации заполнена и кнопка 'Регистрация' нажата.");
    }

    public void clickAgreementCheckBox() {
        logger.info("Нажатие на чекбокс принятия соглашений.");
        Browser.clickElement(RegistrationLocators.AGREEMENT_CHECKBOX);
        logger.debug("Чекбокс принятия соглашений нажат.");
    }
}
