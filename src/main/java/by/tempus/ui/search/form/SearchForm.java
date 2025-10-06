package by.tempus.ui.search.form;

import by.tempus.web.driver.WebDriver;
import org.openqa.selenium.Keys;

public class SearchForm {

    public void sendKeysSearchInput(String text) {
        WebDriver.sendkeysToElement(SearchFormLocators.INPUT_SEARCH, text + Keys.ENTER);
    }

    public void searchFor(String text) {
        sendKeysSearchInput(text);
    }

    public String getSpecificSearchResultText(String expectedText) {
        return WebDriver.getTextFromElement(SearchFormLocators.TITLE_FULL_NAME_SEARCH_RESULT);
    }

    public String getFirstSearchResultText() {
        return WebDriver.getTextFromElement(SearchFormLocators.TITLE_PARTIAL_NAME_SEARCH_RESULT);
    }

    public String getNoResultsMessageText() {
        return WebDriver.getTextFromElement(SearchFormLocators.TITLE_NO_RESULTS);
    }
}
