package by.tempus.ui;

import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.search.form.SearchFormExpectedMessages;
import by.tempus.ui.search.form.SearchForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {
    private SearchForm searchPage;

    @BeforeEach
    public void openHomePage() {
        HomePage homePage = new HomePage();
        homePage.openSite();
        searchPage = new SearchForm();
    }

    @Test
    @DisplayName("Checking that search results are partially relevant to the partial name.")
    public void validFullSearchTest() {
        String searchQuery = "Emporio Armani Sports AR2460";
        searchPage.searchFor(searchQuery);
        Assertions.assertTrue(searchPage.getSpecificSearchResultText(SearchFormExpectedMessages.FULL_SEARCH_RESULT)
                .contains(SearchFormExpectedMessages.FULL_SEARCH_RESULT), "Full search result not found.");
    }

    @Test
    @DisplayName("Checking that search results are partially relevant to the partial name.")
    public void validPartialSearchTest() {
        String partialSearchQuery = "Emporio";
        searchPage.searchFor(partialSearchQuery);
        Assertions.assertTrue(searchPage.getFirstSearchResultText().contains(partialSearchQuery),
                "Expected partial search result to contain: " + partialSearchQuery);
    }

    @Test
    @DisplayName("Checking message when searching for a non-existent query. Проверка сообщения при поиске по несуществующему запросу")
    public void invalidSearchTest() {
        String incorrectSearchQuery = "someinvalidquerry12345";
        searchPage.searchFor(incorrectSearchQuery);
        Assertions.assertTrue(searchPage.getNoResultsMessageText()
                .contains(SearchFormExpectedMessages.NO_RESULTS_MESSAGE), "Invalid search message not displayed.");
    }
}