package ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
            SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            CLEAR_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING_SV}')]",
            SEARCH_RESULT_ELEMENT= "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            EMPTY_SEARCH_RES = "//*[contains(@text, 'No results')]";
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /* Template method */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementInSavedList(String substring)
    {
        return SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL.replace("{SUBSTRING_SV}", substring);
    }
    /* Template method */
    public void initSearchInput()
    {
        this.waitForElementandClick(By.id(SKIP_BUTTON), "Не удалось найти кнопку skip", 1);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementandClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 2);
    }
    public void waitForCancelBtnAppear()
    {
        this.waitForElementPresent(By.id(CLEAR_BUTTON), "Не удлаось найти кнопку очистки поля",1);
    }
    public void waitForCancelBtnDissapear()
    {
        this.waitForElementNotPresent(By.id(CLEAR_BUTTON), "Кнопка очистки поиска еще на месте",1);
    }
    public void cliclCancelBtn()
    {
        this.waitForElementandClick(By.id(CLEAR_BUTTON), "Не удлась нажать на кнопку очистки поиска", 2);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementandSendKeys(By.xpath(SEARCH_INPUT), search_line, "Не удалось отправить значение", 2);
    }
    public void waitForSearchResult(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_Result_Xpath), "Не удалось получить результат с строкой " + substring);
    }
    public void clickByArticleWithSubstring(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementandClick(By.xpath(search_Result_Xpath), "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }
    public void clickByArticleWithSubstringInSavedList(String substring)
    {
        String search_Result_Xpath_SV = getResultSearchElementInSavedList(substring);
        this.waitForElementandClick(By.xpath(search_Result_Xpath_SV), "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }

    public int getAmountOfFindArticles()
    {

        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),"Cannot find anything by this request", 3);
        return this.getAmountofElement(By.xpath(SEARCH_RESULT_ELEMENT));

    }
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(By.xpath(EMPTY_SEARCH_RES), "Результаты поиска не пустые", 3);
    }
    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Что-то найдено");
    }
}
