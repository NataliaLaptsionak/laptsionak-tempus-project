package by.tempus.ui.login.form;

public class LoginPageLocators {
    public static final String LOGIN_FORM_TITLE = "//li[contains(@class, 'is-active')]//button[text()='Вход']";
    public static final String INPUT_LOGIN_EMAIL = "//input[@name='email']";
    public static final String INPUT_LOGIN_PASSWORD = "//input[@name='password']";
    public static final String BUTTON_LOGIN = "//button[@class='button is-primary' and normalize-space()='Войти в аккаунт']";
    public static final String EMPTY_EMAIL_ERROR = "//input[@name='email']/following-sibling::span[@class='form-input__error-message']";
    public static final String EMPTY_PASSWORD_ERROR = "//input[@name='password']/following-sibling::span[@class='form-input__error-message']";
    public static final String LOGIN_CREDENTIALS_ERROR = "//div[@class='success-popup__text']";
    public static final String LABEL_EMAIL_TEXT = "//label[@class='form-input is-required is-email check-email']//span[@class='form-input__placeholder']";
    public static final String LABEL_PASSWORD_TEXT = "//form[@class='form authorize__form js-validate-form']//label[@class='form-input is-required is-password']//span[@class='form-input__placeholder']";
    public static final String LINK_RESTORE_PASSWORD = "//button[@class='form__action j-tabBtnHandle']";
    public static final String RESTORE_PASSWORD_FORM_TITLE_TEXT = "//div[@class='form__recover' and normalize-space()='Для восстановления пароля, введите Email']";
    public static final String TAB_REGISTRATION = "//li[contains(@class, '')]//button[text()='Регистрация']";
}
