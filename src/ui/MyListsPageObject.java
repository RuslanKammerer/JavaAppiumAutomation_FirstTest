package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject
{
    public static final String
            FOLDER_BY_NAME_TEMPL = "//*[contains(@text,'{FOLDER_NAME}')]",
            ARTICLE_BY_TITLE_TEMPL= "//*[@text='{TITLE}']";
    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TEMPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getsSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TEMPL.replace("{TITLE}", article_title);
    }
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementandClick(By.xpath(folder_name_xpath),
                "Не удалось найти сохраненный список статей по имени" + name_of_folder,
                1);
    }
    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElemntToLeft(By.xpath(article_xpath),
                "Не удалось найти статью для удаления");
        this.waitForArticleDissapearByTitle(article_title);
    }
    public void waitForArticleDissapearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Статья еще на месте" + article_title, 2);
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Не могу найти сохраненную статью" + article_title, 2);
    }
}
