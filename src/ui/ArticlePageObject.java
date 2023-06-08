package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            SUB_TITLE = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "//*[contains(@text,'View article in browser')]",
            SAVE_BUTTON = "org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "org.wikipedia:id/snackbar_action",
            INPUT_TEXT = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            NAVIGATE_UP_BTN = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVED_READING_LIST_TMP="//*[contains(@text,'{SAVED_LIST}')]",
            TEST_ELEMENT_PRESENT = "//*[@resource-id='pcs-edit-section-title-description']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    private static String getSavedFolderByName(String name_of_folder)
    {
        return SAVED_READING_LIST_TMP.replace("{SAVED_LIST}", name_of_folder);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(SUB_TITLE), "Не удалось найти подзаголовок статьи", 5);
    }
    public String getArticleName()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeForFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Не удалось свайпнуть к нужному эл-ту", 20);
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementandClick(By.id(SAVE_BUTTON),
                "not find save button",
                2);
        this.waitForElementandClick(By.id(SNACKBAR_ACTION),
                "not find save button",
                2);

        this.waitForElementandSendKeys(By.id(INPUT_TEXT), name_of_folder,
                "Не удалось ввести имя папки для сохранения",
                1);
       this.waitForElementandClick(By.id(OK_BUTTON),
                "not find OK button",
                2);
    }
    public void addArticleToSavedList(String saved_list)
    {
        String saved_articles_list = getSavedFolderByName(saved_list);
        this.waitForElementandClick(By.id(SAVE_BUTTON),
                "not find save button",
                2);
        this.waitForElementandClick(By.id(SNACKBAR_ACTION),
                "not find save button",
                2);
        this.waitForElementandClick(By.xpath(saved_articles_list), "Не выбрать сохраненный список для сохранения", 2);
    }
    public void assertNowElementPresent()
    {
        this.assertElementPresent(By.xpath(TEST_ELEMENT_PRESENT), "Нужный элемент не найден сразу на странице при прогрузке");
    }

    public void closeArticle()
    {
       this.waitForElementandClick(By.xpath(NAVIGATE_UP_BTN),
                "Не удалось выйти назад",
                1);
    }
}
