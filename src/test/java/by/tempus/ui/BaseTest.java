package by.tempus.ui;

import by.tempus.web.driver.WebDriver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import by.tempus.ui.home.page.HomePage;

public class BaseTest {
    protected HomePage homePage;

    @BeforeEach
    @Step("Инициализация страницы HomePage и открытие сайта")
    public void setup() {
        WebDriver.getDriver();
        homePage = new HomePage();
        homePage.openSite();
    }

    @AfterEach
    @Step("Закрытие браузера после завершения теста")
    public void tearDown() {
        clearBrowserData();
        WebDriver.quit();
    }

    private void clearBrowserData() {
        org.openqa.selenium.WebDriver driver = WebDriver.getDriver();
        if (driver != null) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.localStorage.clear();");
            js.executeScript("window.sessionStorage.clear();");
            driver.manage().deleteAllCookies();
        }
    }
}
