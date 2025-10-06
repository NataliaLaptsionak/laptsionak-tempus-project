package by.tempus.ui.cart.page;

public class CartPageLocators {

    public static final String HEADER_CART_ICON = "//a[@class='icons__action icons__action--cart']";
    public static final String CATALOG_BUTTON = "//button[@class=\"header__catalog-btn\"]";
    public static final String CATEGORY_WOMEN = "//div[@class='text-container' and contains(., 'Женские')]";
    public static final String PRODUCT_MICHAEL_KORS_MK7337 = "//a[@title='Michael Kors  Lennox MK7337']";
    public static final String PRODUCT_MICHAEL_KORS_MK7325 = "//a[@title='Michael Kors Runway MK7325']";
    public static final String ADD_TO_CART_BUTTON = "//div[@class=\"product-page-main\"]//div[@class=\"button is-primary is-cart \"]";
    public static final String CLEAR_CART_BUTTON = "//button[@class='clear']";
    public static final String ITEM_INCREASE_QUANTITY_BUTTON = "//div[@class='cart-item-counter__button is-plus']";
    public static final String ITEM_DECREASE_QUANTITY_BUTTON = "//div[@class='cart-item-counter__button is-minus']";
    public static final String CART_ITEMS_LIST = "//span[@class='icons__counter icons__counter--cart']";
    public static final String ITEM_QUANTITY_INPUT = "//input[@class='cart-item-counter__field h6']";
    public static final String EMPTY_CART_MESSAGE = "//div[@class='empty-cart']";
    public static final String FULL_NAME_INPUT = "//input[@id='ORDER_PROP_19']";
    public static final String EMAIL_INPUT = "//input[@id='ORDER_PROP_20']";
    public static final String PHONE_INPUT = "//input[@id='ORDER_PROP_21']";
    public static final String CITY_MINSK_TAG = "//a[@class='quick-location-tag' and contains(., 'Минск')]";
    public static final String PLACE_ORDER_BUTTON = "//a[contains(@class, 'order-page__final-button')]";
    public static final String INCORRECT_EMAIL_CART_ERROR_MESSAGE = "//font[normalize-space()='Некорректный E-Mail']";
    public static final String EMPTY_PHONE_CART_ERROR_MESSAGE = "//font[normalize-space()='\"Телефон\": обязательно для заполнения']";
}
