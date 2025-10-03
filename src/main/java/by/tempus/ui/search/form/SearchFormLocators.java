package by.tempus.ui.search.form;

public class SearchFormLocators {
    public static final String INPUT_SEARCH = "//input[@id='title-search-input']";
    public static final String BUTTON_SEARCH = "//input[@id='title-search-input']/following-sibling::button";
    public static final String TITLE_FULL_NAME_SEARCH_RESULT = "//a[@class='product-card__name' and contains(text(), 'Emporio Armani Sports AR2460')]";
    public static final String TITLE_PARTIAL_NAME_SEARCH_RESULT = "//a[@class='product-card__name']";
    public static final String TITLE_NO_RESULTS = "//div[@class='container' and contains(., 'Сожалеем, но ничего не найдено.')]";
}
