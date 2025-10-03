package by.tempus.ui.cart.page;

import by.tempus.web.driver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    public void clickCatalogButton() {
        WebDriver.clickElement(CartPageLocators.CATALOG_BUTTON);
    }

    public void clickWomenCategory() {
        WebDriver.clickElement(CartPageLocators.CATEGORY_WOMEN);
    }

    public void selectFirstMichaelKorsWatch() {
        WebDriver.clickElement(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7337);
    }

    public void selectSecondMichaelKorsWatch() {
        WebDriver.clickElement(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7325);
    }

    public CartPage clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ADD_TO_CART_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public void openCart() {
        WebDriver.clickElement(CartPageLocators.HEADER_CART_ICON);
    }

    public void increaseQuantity() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_INCREASE_QUANTITY_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void decreaseQuantity() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_DECREASE_QUANTITY_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void clearCart() {
        WebDriver.clickElement(CartPageLocators.CLEAR_CART_BUTTON);
    }

    public void fillCheckoutForm(String fullName, String email, String phone) {
        WebDriver.sendkeysToElement(CartPageLocators.FULL_NAME_INPUT, fullName);
        WebDriver.sendkeysToElement(CartPageLocators.EMAIL_INPUT, email);
        WebDriver.sendkeysToElement(CartPageLocators.PHONE_INPUT, phone);
    }

    public void selectCityMinsk() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.CITY_MINSK_TAG)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void clickGoToCatalogFromEmptyCart() {
        WebDriver.clickElement(CartPageLocators.GO_TO_CATALOG_BUTTON_FROM_EMPTY_CART);
    }

    public int getCartItemCount() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CartPageLocators.CART_ITEMS_LIST)));
            return WebDriver.getDriver().findElements(By.xpath(CartPageLocators.CART_ITEMS_LIST)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getItemQuantity() {
        return WebDriver.findElement(CartPageLocators.ITEM_QUANTITY_INPUT).getAttribute("value");
    }

    public String getEmptyCartMessageText() {
        return WebDriver.getTextFromElement(CartPageLocators.EMPTY_CART_MESSAGE);
    }

    public void clickPlaceOrderButton() {
        WebDriver.clickElement(CartPageLocators.PLACE_ORDER_BUTTON);
    }

    public String getIncorrectEmailErrorMessage() {
        return WebDriver.getTextFromElement(CartPageLocators.INCORRECT_EMAIL_CART_ERROR_MESSAGE);
    }

    public String getEmptyPhoneErrorMessage() {
        return WebDriver.getTextFromElement(CartPageLocators.EMPTY_PHONE_CART_ERROR_MESSAGE);
    }
}
