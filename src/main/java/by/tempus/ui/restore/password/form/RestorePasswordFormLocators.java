package by.tempus.ui.restore.password.form;

public class RestorePasswordFormLocators {
    public static final String RESTORE_PASSWORD_FORM_TEXT = "//div[@class='form__recover' and normalize-space()='Для восстановления пароля, введите Email']";
    public static final String BUTTON_SUBMIT_RESTORE = "//form[@class=\"form restore__form js-validate-form\"]//button[@class=\"button is-primary\"]";
    public static final String RESTORE_PASSWORD_FORM_EMAIL_ERROR = "//span[@class=\"form-input__error-message\"]";
    public static final String LOGIN_FORM_TITLE = "//li[contains(@class, 'is-active')]//button[text()='Вход']";
    public static final String TAB_REGISTRATION = "//li[contains(@class, '')]//button[text()='Регистрация']";
    public static final String LABEL_EMAIL_FIELD = "//form[@class=\"form restore__form js-validate-form\"]//label[@class=\"form-input is-required is-email\"]";
    public static final String INPUT_RESTORE_PASSWORD_EMAIL = "(//input[@type=\"email\"])[3]";
    public static final String RESTORE_PASSWORD_UNREGISTERED_EMAIL_ERROR = "//div[@class='success-popup__text']";
}
