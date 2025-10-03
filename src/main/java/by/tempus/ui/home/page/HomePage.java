package by.tempus.ui.home.page;

import by.tempus.web.driver.WebDriver;
import static by.tempus.ui.home.page.HomePageLocators.*;

public class HomePage {

    public HomePage() {
    }

    public HomePage openSite() {
        WebDriver.getDriver().navigate().to(URL);
        return this;
    }

    public void clickButtonLogin() {
        WebDriver.clickElement(HomePageLocators.BUTTON_LOGIN);
    }
}
