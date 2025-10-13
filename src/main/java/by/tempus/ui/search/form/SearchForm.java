package by.tempus.ui.search.form;

import by.tempus.web.driver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;

public class SearchForm {
    private static final Logger logger = LogManager.getLogger(SearchForm.class);

    public void sendKeysSearchInput(String text) {
        logger.info("Ввод текста в поле поиска: {}", text);
        Browser.sendKeysToElement(SearchFormLocators.INPUT_SEARCH, text + Keys.ENTER);
    }

    public void searchFor(String text) {
        logger.info("Выполнение поиска по запросу: {}", text);
        sendKeysSearchInput(text);
    }

    public String getSpecificSearchResultText(String expectedText) {
        logger.info("Получение результата поиска: {}", expectedText);
        return Browser.getTextFromElement(SearchFormLocators.TITLE_FULL_NAME_SEARCH_RESULT);
    }

    public String getPartialNameSearchResultText() {
        logger.info("Получение текста  результата поиска.");
        return Browser.getTextFromElement(SearchFormLocators.TITLE_PARTIAL_NAME_SEARCH_RESULT);
    }

    public String getNoResultsMessageText() {
        logger.info("Получение сообщения об отсутствии результатов поиска.");
        return Browser.getTextFromElement(SearchFormLocators.TITLE_NO_RESULTS);
    }
}
