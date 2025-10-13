package by.tempus.ui;

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
    private SearchForm searchForm;

    @BeforeEach
    @Step("Setup Search Form")
    public void searchSetUp() {
        searchForm = new SearchForm();
    }

    @Test
    @DisplayName("Checking that search results are relevant to the product full name.")
    @Story("Search Results Relevance")
    @Description("Verifies that search results contain the full expected result when searching by a product full name.")
    @Severity(SeverityLevel.NORMAL)
    public void validFullSearchTest() {
        String searchQuery = "Emporio Armani Sports AR2460";
        searchForm.searchFor(searchQuery);

        Assertions.assertTrue(searchForm.getSpecificSearchResultText(SearchFormExpectedMessages.FULL_SEARCH_RESULT)
                .contains(SearchFormExpectedMessages.FULL_SEARCH_RESULT), "Search result did not contain the expected full product name.");
    }

    @Test
    @DisplayName("Checking that search results are partially relevant to the partial name.")
    @Story("Search Results Relevance")
    @Description("Verifies that search results contain the partial query itself when searching by a partial name.")
    @Severity(SeverityLevel.NORMAL)
    public void validPartialSearchTest() {
        String partialSearchQuery = "Emporio";
        searchForm.searchFor(partialSearchQuery);

        Assertions.assertTrue(searchForm.getPartialNameSearchResultText().contains(partialSearchQuery),
                "Search result did not contain the expected partial query");
    }

    @Test
    @DisplayName("Checking message when searching for a non-existent query.")
    @Story("Search Error Handling")
    @Description("Verifies that an appropriate message is displayed when searching for a query that yields no results.")
    @Severity(SeverityLevel.NORMAL)
    public void invalidSearchTest() {
        String incorrectSearchQuery = "someinvalidquerry12345";
        searchForm.searchFor(incorrectSearchQuery);

        Assertions.assertTrue(searchForm.getNoResultsMessageText()
                .contains(SearchFormExpectedMessages.NO_RESULTS_MESSAGE), "No results message did not match expected.");
    }
}
