package by.tempus.ui.registration.form;

public class RegistrationLocators {

    public static final String TAB_REGISTRATION = "//li[contains(@class, '')]//button[text()='Регистрация']";
    public static final String INPUT_REGISTRATION_FULL_NAME = "//input[@name=\"fullName\"]";
    public static final String INPUT_REGISTRATION_EMAIL = "//form[@class=\"form registration__form js-validate-form\"]//input[@name=\"email\"]";
    public static final String INPUT_REGISTRATION_PHONE = "//input[@type=\"tel\"]";
    public static final String INPUT_REGISTRATION_PASSWORD = "//label[@class=\"form-input is-required is-password is-error\"]//input[@type=\"password\"]";
    public static final String INPUT_REGISTRATION_REPEAT_PASSWORD = "//label[@class=\"form-input is-required is-confirm-password\"]//input[@type=\"password\"]";
    public static final String EMPTY_FULL_NAME_ERROR = "//label[@class=\"form-input is-required is-error\"]//span[@class=\"form-input__error-message\"]";
    public static final String EMPTY_EMAIL_ERROR = "//label[@class=\"form-input is-required is-email is-error\"]//span[@class=\"form-input__error-message\"]";
    public static final String INCORRECT_EMAIL_ERROR = "//span[@class=\"form-input__error-message\"]";
    public static final String INCORRECT_REPEAT_PASSWORD_TEXT = "(//span[@class=\"form-input__error-message\"])[3]";
    public static final String EMPTY_PHONE_ERROR = "//label[@class=\"form-input is-required is-phone phone-input is-error\"]//span[@class=\"form-input__error-message\"]";
    public static final String EMPTY_PASSWORD_ERROR = "//label[@class=\"form-input is-required is-password is-error\"]//span[@class=\"form-input__error-message\"]";
    public static final String REGISTRATION_REPEAT_PASSWORD_ERROR = "//label[@class=\"form-input is-required is-confirm-password is-error\"]//span[@class=\"form-input__error-message\"]";
    public static final String BUTTON_REGISTRATION = "//form[@class=\"form registration__form js-validate-form\"]//button[@type=\"submit\"]";
    public static final String LABEL_FULL_NAME_FIELD_TEXT = "//form[@class=\"form registration__form js-validate-form\"]//label[@class=\"form-input is-required\"]//span[@class=\"form-input__placeholder\"]";
    public static final String LABEL_EMAIL_FIELD_TEXT = "//form[@class=\"form registration__form js-validate-form\"]//label[@class=\"form-input is-required is-email\"]//span[@class=\"form-input__placeholder\"]";
    public static final String LABEL_PHONE_FIELD_TEXT = "//form[@class=\"form registration__form js-validate-form\"]//label[@class=\"form-input is-required is-phone phone-input\"]//span[@class=\"form-input__placeholder\"]";
    public static final String LABEL_PASSWORD_FIELD_TEXT = "//form[@class=\"form registration__form js-validate-form\"]//label[@class=\"form-input is-required is-password\"]//span[@class=\"form-input__placeholder\"]";
    public static final String LABEL_REPEAT_PASSWORD_FIELD_TEXT = "//label[@class=\"form-input is-required is-confirm-password\"]//span[@class=\"form-input__placeholder\"]";
    public static final String AGREEMENT_CHECKBOX = "//span[@class=\"checkbox-input__check\"]";
    public static final String AGREEMENT_CHECKBOX_ERROR = "//div[@class=\"form__agreement\"]//span[@class=\"form-input__error-message\"]";
    public static final String AGREEMENT_CHECKBOX_TEXT = "//form[@class=\"form registration__form js-validate-form\"]//span[@class=\"agreement__text\"]";
}
