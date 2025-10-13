package by.tempus.ui.home.page;

import by.tempus.web.driver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {
        logger.info("Инициализирован объект HomePage.");
    }

    public void clickButtonLogin() {
        logger.info("Нажатие на кнопку 'Вход' (BUTTON_LOGIN).");
        Browser.clickElement(HomePageLocators.BUTTON_LOGIN);
        logger.debug("Кнопка 'Вход' (BUTTON_LOGIN) нажата.");
    }
}
