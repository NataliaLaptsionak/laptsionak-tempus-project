package by.tempus.ui;

import by.tempus.ui.home.page.HomePage;
import by.tempus.web.driver.Browser;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;

import static by.tempus.ui.home.page.HomePageLocators.URL;

public class BaseTest {
    protected static final HomePage homePage = new HomePage();

    @BeforeEach
    @Step("Инициализация страницы HomePage и открытие сайта")
    public void setup() {
        Browser.getDriver().navigate().to(URL);
    }

    private void clearBrowserData() {
        if (Browser.getDriver() != null) {
            JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
            js.executeScript("window.localStorage.clear();");
            js.executeScript("window.sessionStorage.clear();");
        }
    }

    @AfterEach
    @Step("Закрытие браузера после завершения теста")
    public void tearDown() {
        clearBrowserData();
        Browser.getDriver().manage().deleteAllCookies();
        Browser.quit();

    }
}
