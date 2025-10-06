package by.tempus.ui;

import by.tempus.resources.DataGenerator;
import by.tempus.ui.cart.page.CartPage;
import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.cart.page.CartPageExpectedMessages;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

@Epic("UI Testing")
@Feature("Shopping Cart")
public class CartTest extends BaseTest {
    private CartPage cartPage;

    @BeforeEach
    @Step("Initialize Home and Cart pages, open site")
    public void setUp() {
        HomePage homePage = new HomePage();
        cartPage = new CartPage();
        homePage.openSite();
    }

    @Test
    @Story("Empty Cart Scenario")
    @Description("Checks that an empty shopping cart displays the correct message.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify empty cart display")
    public void viewEmptyCartTest() {
        cartPage.openCart();

        Assertions.assertEquals(CartPageExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessageText());
    }

    @Test
    @Story("Adding Items to Cart")
    @Description("Checks that multiple distinct items can be added to the shopping cart correctly.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify adding multiple different items to cart")
    public void addMultipleItemsToCartTest() {
        cartPage.clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.clickAddToCart().clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectSecondMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();

        Assertions.assertEquals(2, cartPage.getCartItemCount());
    }

    @Test
    @DisplayName("Verify increasing and decreasing item quantity")
    @Story("Managing Cart Quantity")
    @Description("Checks that item quantity in the cart can be increased and decreased correctly.")
    @Severity(SeverityLevel.NORMAL)
    public void increaseAndDecreaseItemQuantityTest() throws InterruptedException {

        cartPage.clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();

        Assertions.assertEquals("1", cartPage.getItemQuantity());

        cartPage.increaseQuantity();
        sleep(1000);

        Assertions.assertEquals("2", cartPage.getItemQuantity());

        cartPage.decreaseQuantity();
        sleep(1000);

        Assertions.assertEquals("1", cartPage.getItemQuantity());
    }

    @Test
    @DisplayName("Verify complete cart clearing")
    @Story("Emptying Cart")
    @Description("Checks that the shopping cart can be completely cleared and displays the empty cart message.")
    @Severity(SeverityLevel.NORMAL)
    public void clearCartTest() {
        cartPage.clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();
        cartPage.clearCart();
        cartPage.openCart();

        Assertions.assertEquals(CartPageExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessageText());
    }

    @Test
    @DisplayName("Verify error on checkout with invalid E-mail")
    @Story("Checkout Process Failures")
    @Description("Checks that an invalid email during checkout displays the correct error message.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyErrorOnCheckoutWithInvalidEmailTest() {
        cartPage.clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();
        cartPage.fillCheckoutForm(DataGenerator.generateValidFullName(), DataGenerator.generateIncorrectEmail(), DataGenerator.generateValidPassword());
        cartPage.selectCityMinsk();
        cartPage.clickPlaceOrderButton();

        Assertions.assertEquals(CartPageExpectedMessages.INVALID_EMAIL_ERROR, cartPage.getIncorrectEmailErrorMessage());
    }

    @Test
    @DisplayName("Verify error on checkout with empty phone number")
    @Story("Checkout Process Failures")
    @Description("Checks that an empty phone number during checkout displays the correct error message.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyErrorOnCheckoutWithEmptyPhoneNumberTest() {
        cartPage.clickCatalogButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();
        cartPage.fillCheckoutForm(DataGenerator.generateValidFullName(), DataGenerator.generateValidEmail(), "");
        cartPage.selectCityMinsk();
        cartPage.clickPlaceOrderButton();

        Assertions.assertEquals(CartPageExpectedMessages.EMPTY_PHONE_FIELD_ERROR, cartPage.getEmptyPhoneErrorMessage(), "");
    }
}
