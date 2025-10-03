package by.tempus.ui.cart.page;

public class CartPageLocators {

    public static final String HEADER_CART_ICON = "//a[@class='icons__action icons__action--cart']";
    public static String CATALOG_BUTTON = "//button[@class=\"header__catalog-btn\"]";
    public static String CATEGORY_WOMEN = "//div[@class='text-container' and contains(., 'Женские')]";
    public static String PRODUCT_MICHAEL_KORS_MK7337 = "//a[@title='Michael Kors  Lennox MK7337']";
    public static String PRODUCT_MICHAEL_KORS_MK7325 = "//a[@title='Michael Kors Runway MK7325']";
    public static String ADD_TO_CART_BUTTON = "//div[@class=\"product-page-main\"]//div[@class=\"button is-primary is-cart \"]";
    public static String CLEAR_CART_BUTTON = "//button[@class='clear']";
    public static String ITEM_INCREASE_QUANTITY_BUTTON = "//div[@class='cart-item-counter__button is-plus']";
    public static String ITEM_DECREASE_QUANTITY_BUTTON = "//div[@class='cart-item-counter__button is-minus']";
    public static String CART_ITEMS_LIST = "//span[@class='icons__counter icons__counter--cart']";
    public static String ITEM_QUANTITY_INPUT = "//input[@class='cart-item-counter__field h6']";
    public static String EMPTY_CART_MESSAGE = "//div[@class='empty-cart']";
    public static String GO_TO_CATALOG_BUTTON_FROM_EMPTY_CART = "//a[@class='catalog-button']";
    public static String FULL_NAME_INPUT = "//input[@id='ORDER_PROP_19']";
    public static String EMAIL_INPUT = "//input[@id='ORDER_PROP_20']";
    public static String PHONE_INPUT = "//input[@id='ORDER_PROP_21']";
    public static String CITY_MINSK_TAG = "//a[@class='quick-location-tag' and contains(., 'Минск')]";
    public static String PLACE_ORDER_BUTTON = "//a[contains(@class, 'order-page__final-button')]";
    public static String INCORRECT_EMAIL_CART_ERROR_MESSAGE = "//font[normalize-space()='Некорректный E-Mail']";
    public static String EMPTY_PHONE_CART_ERROR_MESSAGE = "//font[normalize-space()='\"Телефон\": обязательно для заполнения']";
}
