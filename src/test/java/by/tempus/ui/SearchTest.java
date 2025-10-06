package by.tempus.ui;

import by.tempus.ui.home.page.HomePage;
import by.tempus.ui.search.form.SearchFormExpectedMessages;
import by.tempus.ui.search.form.SearchForm;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Testing")
@Feature("Search Functionality")
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
    @Story("Search Results Relevance")
    @Description("Verifies that search results contain the full expected result when searching by a partial name like 'Armani'.")
    @Severity(SeverityLevel.NORMAL)
    public void validFullSearchTest() {
        String searchQuery = "Emporio Armani Sports AR2460";
        searchPage.searchFor(searchQuery);

        Assertions.assertTrue(searchPage.getSpecificSearchResultText(SearchFormExpectedMessages.FULL_SEARCH_RESULT)
                .contains(SearchFormExpectedMessages.FULL_SEARCH_RESULT), "Full search result not found.");
    }

    @Test
    @DisplayName("Checking that search results are partially relevant to the partial name.")
    @Story("Search Results Relevance")
    @Description("Verifies that search results contain the partial query itself when searching by a partial name.")
    @Severity(SeverityLevel.NORMAL)
    public void validPartialSearchTest() {
        String partialSearchQuery = "Emporio";
        searchPage.searchFor(partialSearchQuery);

        Assertions.assertTrue(searchPage.getFirstSearchResultText().contains(partialSearchQuery),
                "Expected partial search result to contain: " + partialSearchQuery);
    }

    @Test
    @DisplayName("Checking message when searching for a non-existent query.")
    @Story("Search Error Handling")
    @Description("Verifies that an appropriate message is displayed when searching for a query that yields no results.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidSearchTest() {
        String incorrectSearchQuery = "someinvalidquerry12345";
        searchPage.searchFor(incorrectSearchQuery);

        Assertions.assertTrue(searchPage.getNoResultsMessageText()
                .contains(SearchFormExpectedMessages.NO_RESULTS_MESSAGE), "Invalid search message not displayed.");
    }
}
