package by.tempus.ui;

import by.tempus.ui.cart.page.CartPage;
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
    public void cartSetUp() {
        cartPage = new CartPage();
    }

    @Test
    @Story("Empty Cart Scenario")
    @Description("Checks that an empty shopping cart displays the correct message.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify empty cart display")
    public void viewEmptyCartTest() {
        cartPage.openCart();

        Assertions.assertEquals(CartPageExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessageText(),
                "Expected empty cart message did not match actual message.");
    }

    @Test
    @Story("Adding Items to Cart")
    @Description("Checks that multiple distinct items can be added to the shopping cart correctly.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify adding multiple different items to cart")
    public void addMultipleItemsToCartTest() {
        cartPage.clickWatchButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.clickWatchButton();
        cartPage.clickWomenCategory();
        cartPage.selectSecondMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();

        Assertions.assertEquals(2, cartPage.getCartItemCount(),
                "Expected cart to contain 2 items, but actual count was different");
    }

    @Test
    @DisplayName("Verify increasing and decreasing item quantity")
    @Story("Managing Cart Quantity")
    @Description("Checks that item quantity in the cart can be increased and decreased correctly.")
    @Severity(SeverityLevel.NORMAL)
    public void increaseAndDecreaseItemQuantityTest() throws InterruptedException {
        cartPage.clickWatchButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();

        Assertions.assertEquals("1", cartPage.getItemQuantity(), "Expected item quantity to be 1 after initial increase, but was different");

        cartPage.increaseQuantity();
        sleep(1000);

        Assertions.assertEquals("2", cartPage.getItemQuantity(), "Expected item quantity to be 2 after second increase, but was different");

        cartPage.decreaseQuantity();
        sleep(1000);

        Assertions.assertEquals("1", cartPage.getItemQuantity(), "Expected item quantity to be 1 after initial increase, but was different");
    }

    @Test
    @DisplayName("Verify complete cart clearing")
    @Story("Emptying Cart")
    @Description("Checks that the shopping cart can be completely cleared and displays the empty cart message.")
    @Severity(SeverityLevel.NORMAL)
    public void clearCartTest() {
        cartPage.clickWatchButton();
        cartPage.clickWomenCategory();
        cartPage.selectFirstMichaelKorsWatch();
        cartPage.clickAddToCart();
        cartPage.openCart();
        cartPage.clearCart();
        cartPage.openCart();

        Assertions.assertEquals(CartPageExpectedMessages.EMPTY_CART_MESSAGE, cartPage.getEmptyCartMessageText(), "Expected empty cart message after clearing did not match actual message");
    }
}
