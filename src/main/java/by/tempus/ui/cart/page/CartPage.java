package by.tempus.ui.cart.page;

import by.tempus.utils.JavaScriptExecutor;
import by.tempus.web.driver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage() {
        logger.info("Инициализирован объект CartPage.");
    }

    public void clickWatchButton() {
        logger.info("Нажатие на кнопку 'Часы'");
        Browser.clickElement(CartPageLocators.WATCH_BUTTON);
        logger.debug("Кнопка 'Часы' нажата.");
    }

    public void clickWomenCategory() {
        logger.info("Выбор категории 'Женские'");
        Browser.clickElement(CartPageLocators.CATEGORY_WOMEN);
        logger.debug("Категория 'Женские' выбрана.");
    }

    public void selectFirstMichaelKorsWatch() {
        logger.info("Нажатие на товар Michael Kors Lennox MK7337.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7337)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("Товар Michael Kors Lennox MK7337 нажат.");
    }

    public void selectSecondMichaelKorsWatch() {
        logger.info("Нажатие на товар Michael Kors Runway MK7325.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.PRODUCT_MICHAEL_KORS_MK7325)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("товар Michael Kors Runway MK7325 нажат.");
    }

    public void clickAddToCart() {
        logger.info("Нажатие на кнопку 'Добавить в корзину'.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ADD_TO_CART_BUTTON)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("Кнопка 'Добавить в корзину' нажата.");
    }

    public void openCart() {
        logger.info("Открытие корзины.");
        Browser.clickElement(CartPageLocators.HEADER_CART_ICON);
        logger.debug("Корзина открыта.");
    }

    public void increaseQuantity() {
        logger.info("Увеличение количества товара в корзине.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_INCREASE_QUANTITY_BUTTON)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("Количество товара увеличено.");
    }

    public void decreaseQuantity() {
        logger.info("Уменьшение количества товара в корзине.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.ITEM_DECREASE_QUANTITY_BUTTON)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("Количество товара уменьшено.");
    }

    public void clearCart() {
        logger.info("Нажатие на кнопку 'Очистить корзину'.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CartPageLocators.CLEAR_CART_BUTTON)));
        JavaScriptExecutor.getJavaScriptExecutor("arguments[0].click();", element);
        logger.debug("Кнопка 'Очистить корзину' нажата.");
    }

    public int getCartItemCount() {
        logger.info("Получение количества товаров в корзине.");
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CartPageLocators.CART_ITEMS_LIST)));
            return Browser.getDriver().findElements(By.xpath(CartPageLocators.CART_ITEMS_LIST)).size();
        } catch (Exception e) {
            logger.warn("Не удалось получить количество товаров из корзины. Возвращаем 0. Ошибка: {}", e.getMessage());
            return 0;
        }
    }

    public String getItemQuantity() {
        logger.info("Получение текстового количества товаров в корзине.");
        return Browser.findElement(CartPageLocators.ITEM_QUANTITY_INPUT).getAttribute("value");
    }

    public String getEmptyCartMessageText() {
        logger.info("Получение сообщения о пустой корзине.");
        return Browser.getTextFromElement(CartPageLocators.EMPTY_CART_MESSAGE);
    }
}
