package by.tempus.web.driver;

import by.tempus.utils.JsScriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {
    private static final int AMOUNT_OF_WAIT = 10;
    private static WebDriver driver;
    public static WebDriverWait wait;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getInstance() {
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebElement findElement(String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(AMOUNT_OF_WAIT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void sendKeysToElement(String xpath, String value) {
        WebElement element = findElement(xpath);
        element.clear();
        element.sendKeys(value);
    }

    public static String getTextFromElement(String xpath) {
        WebElement element = findElement(xpath);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return findElement(xpath).getText();
    }

    public static void scrollToElement(String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(AMOUNT_OF_WAIT));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickElement(String xpath) {
        WebElement element = findElement(xpath);
        JsScriptExecutor.getJavaScriptExecutor("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
