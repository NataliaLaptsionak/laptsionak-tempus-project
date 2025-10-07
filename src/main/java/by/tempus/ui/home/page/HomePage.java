package by.tempus.ui.home.page;

import by.tempus.web.driver.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tempus.ui.home.page.HomePageLocators.*;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {
        logger.info("Инициализирован объект HomePage.");
    }

    public HomePage openSite() {
        logger.info("Открытие сайта по URL: {}", URL);
        WebDriver.getDriver().navigate().to(URL);
        logger.debug("Сайт успешно открыт.");
        return this;
    }

    public void clickButtonLogin() {
        logger.info("Нажатие на кнопку 'Вход' (BUTTON_LOGIN).");
        WebDriver.clickElement(HomePageLocators.BUTTON_LOGIN);
        logger.debug("Кнопка 'Вход' (BUTTON_LOGIN) нажата.");
    }
}
