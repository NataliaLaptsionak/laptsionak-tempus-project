package by.tempus.ui.cart.page;

import by.tempus.web.driver.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private static final Logger logger = LogManager.getLogger(CartPage.class);


    public void clickCatalogButton() {
        logger.info("Нажатие на кнопку 'Каталог'");
        WebDriver.clickElement(CartPageLocators.CATALOG_BUTTON);
        logger.debug("Кнопка 'Категория' нажата.");
    }

    public void clickWomenCategory() {
        logger.info("Выбор категории 'Женские'");
        WebDriver.clickElement(CartPageLocators.CATEGORY_WOMEN);
        logger.debug("Категория 'Женские' выбрана.");
    }

    public void selectFirstMichaelKorsWatch() {
        logger.info("Нажатие на первый актуальный товар.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7337)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Первый актуальный товар нажат.");
    }

    public void selectSecondMichaelKorsWatch() {
        logger.info("Нажатие на второй актуальный товар.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7325)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Второй актуальный товар нажат.");
    }

    public CartPage clickAddToCart() {
        logger.info("Нажатие на кнопку 'Добавить в корзину'.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ADD_TO_CART_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Кнопка 'Добавить в корзину' нажата.");
        return this;
    }

    public void openCart() {
        logger.info("Открытие корзины.");
        WebDriver.clickElement(CartPageLocators.HEADER_CART_ICON);
        logger.debug("Корзина открыта.");
    }

    public void increaseQuantity() {
        logger.info("Увеличение количества товара в корзине.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_INCREASE_QUANTITY_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Количество товара увеличено.");
    }

    public void decreaseQuantity() {
        logger.info("Уменьшение количества товара в корзине.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_DECREASE_QUANTITY_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Количество товара уменьшено.");
    }

    public void clearCart() {
        logger.info("Нажатие на кнопку 'Очистить корзину'.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.CLEAR_CART_BUTTON)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Кнопка 'Очистить корзину' нажата.");
    }

    public void fillCheckoutForm(String fullName, String email, String phone) {
        logger.info("Заполнение формы оформления заказа с данными: fullName='{}', email='{}', phone='{}'", fullName, email, phone);
        WebDriver.sendkeysToElement(CartPageLocators.FULL_NAME_INPUT, fullName);
        WebDriver.sendkeysToElement(CartPageLocators.EMAIL_INPUT, email);
        WebDriver.sendkeysToElement(CartPageLocators.PHONE_INPUT, phone);
        logger.debug("Форма оформления заказа заполнена.");
    }

    public void selectCityMinsk() {
        logger.info("Выбор города.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.CITY_MINSK_TAG)));
        JavascriptExecutor js = (JavascriptExecutor) WebDriver.getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.debug("Город выбран.");
    }

    public int getCartItemCount() {
        logger.info("Получение количества товаров в корзине.");
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CartPageLocators.CART_ITEMS_LIST)));
            return WebDriver.getDriver().findElements(By.xpath(CartPageLocators.CART_ITEMS_LIST)).size();
        } catch (Exception e) {
            logger.warn("Не удалось получить количество товаров из корзины. Возвращаем 0. Ошибка: {}", e.getMessage());
            return 0;
        }
    }

    public String getItemQuantity() {
        logger.info("Получение текстового количества товаров в корзине.");
        return WebDriver.findElement(CartPageLocators.ITEM_QUANTITY_INPUT).getAttribute("value");
    }

    public String getEmptyCartMessageText() {
        logger.info("Получение сообщения о пустой корзине.");
        return WebDriver.getTextFromElement(CartPageLocators.EMPTY_CART_MESSAGE);
    }

    public void clickPlaceOrderButton() {
        logger.info("Нажатие на кнопку 'Оформить заказ'.");
        WebDriver.wait.until(ExpectedConditions.elementToBeClickable(WebDriver.findElement(CartPageLocators.PLACE_ORDER_BUTTON)));
        WebDriver.clickElement(CartPageLocators.PLACE_ORDER_BUTTON);
        logger.debug("Кнопка 'Оформить заказ' нажата.");
    }

    public String getIncorrectEmailErrorMessage() {
        logger.info("Получение сообщения о некорректном Email.");
        return WebDriver.getTextFromElement(CartPageLocators.INCORRECT_EMAIL_CART_ERROR_MESSAGE);
    }

    public String getEmptyPhoneErrorMessage() {
        logger.info("Получение сообщения о пустом номере телефона.");
        return WebDriver.getTextFromElement(CartPageLocators.EMPTY_PHONE_CART_ERROR_MESSAGE);
    }
}
