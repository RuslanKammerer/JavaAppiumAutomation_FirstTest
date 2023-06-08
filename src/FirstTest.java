import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.*;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    @Test
    public void testSearch() throws Exception {

        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelBtnAppear();
        SearchPageObject.cliclCancelBtn();
        SearchPageObject.waitForCancelBtnDissapear();
    }
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        //MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не удалось найти java", 1);
        //MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"), "not_find", 3);
        //WebElement title_name = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "Не удалось найти заголовок статьи", 5);
        //String article_title = title_name.getAttribute("text");
        assertEquals("Title is not match", "Java (programming language)", article_title);

    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeForFooter();
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        //MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Appium", "Не удалось найти java", 1);
        //MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"), "not_find", 3);
        //MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Automation for Apps')]"), "Не удалось найти заголовок статьи", 5);
        //MainPageObject.swipeUpToFindElement(By.xpath("//*[contains(@text,'View article in browser')]"), "Не удалось свайпом найти свайпом что-то", 20);
    }
    @Test
    public void testfindArticlesandClose()
            // В данном тест-кейсе ищется 3 статьи по слову Carbon, стирается строка пропуска и проверяется, что в поле выдачи пусто
    {
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Carbon", "Не удалось отправить значение для поиска", 1);
        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Carbon dioxide')]"), "Не удалось найти элемент по названию", 2);
        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Carbon nanotube')]"), "Не удалось найти элемент по названию", 2);
        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Carbohydrate')]"), "Не удалось найти элемент по названию", 2);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "не удалось очистить элемент", 2);
        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "не удалось найти поле поиска", 2);
        MainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text,'Carbon dioxide')]"), "Не удалось найти элемент по названию", 2);
    }
    @Test
    public void testsaveFirstArticleToList()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();
        String name_of_folder = "Test1";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUi.clickToMyLists();

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveAndDeleteArticles()
    {
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска интро",
                1);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"),
                "не удалось найти id поля поиска статей",
                2);
        MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Не удалось ввести значения для поиска статьи",
                1);
        MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"),
                "Не удалось найти статью",
                3);
        WebElement title_name = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти заголовок статьи",
                5);

        String article_name = title_name.getAttribute("text");

        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/page_save"),
                "Не найдена кнопка для сохранения статьи",
                2);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/snackbar_action"),
                "Не найдена кнопка во всплывающем снек-баре",
                2);

        String name_of_folder = "Test1";

        MainPageObject.waitForElementandSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder,
                "Не удалось ввести имя папки для сохранения",
                1);
        MainPageObject.waitForElementandClick(By.id("android:id/button1"),
                "Не найдена кнопка ОК в диалоге",
                2);
        MainPageObject.waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти в список статей",
                1);
        MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island in Indonesia']"),
                "Не удалось найти статью",
                3);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/page_save"),
                "Не найдена кнопка для сохранения статьи",
                2);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/snackbar_action"),
                "Не найдена кнопка во всплывающем снек-баре",
                2);
        MainPageObject.waitForElementandClick(By.xpath("//*[contains(@text,'"+name_of_folder+"')]"),
                "Не удалось найти сохраненный список статей",
                1);
        MainPageObject.waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        MainPageObject.waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Не найдена кнопка навигации для сохраненных статей",
                2);
        MainPageObject.waitForElementandClick(By.xpath("//*[contains(@text,'"+name_of_folder+"')]"),
                "Не удалось найти сохраненный список статей",
                1);
        MainPageObject.swipeElemntToLeft(By.xpath("//*[@text='Island in Indonesia']"),
                "Не удалось найти статью для удаления");
        MainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text,'You have no articles added to this list.')]"),
                "Список статей пустой",
                2);
        MainPageObject.waitForElementandClick(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти статью в сохраненном списке статей",
                3);
        WebElement title_name_check = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти заголовок сохраненной статьи",
                5);
        String article_name_check = title_name_check.getAttribute("text");
        assertEquals("Искомый заголовок статьи не совпадает с сохраненным",article_name,article_name_check);
    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);

        String search_line ="Linkin park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        assertTrue("Страницы не найдены", amount_of_search_res>0);

    }
    @Test
    public void testAmountofEmptySearch()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "blablablablabla";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultSearch();

    }
    @Test
    public void testTitlePage()
    {
        String search_line ="Java";
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), search_line, "Не удалось отправить значение для поиска", 1);
        MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"), "Не удалось кликнуть на нужную статью", 2);
        String target_element = "//*[@resource-id='pcs-edit-section-title-description']";
        //waitForElementPresent(By.xpath(target_element), "элемент не найден");
        MainPageObject.assertElementPresent(By.xpath(target_element), "Нужный элемент не найден на странице");
    }
    @Test
    public void testTitleScreenOrientation()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String search_line ="Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String title_decription_wert = ArticlePageObject.getArticleName();

        this.rotateScreenLandscape();

        String title_decription_hor = ArticlePageObject.getArticleName();
        assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_hor );

        this.rotateScreenPortrait();
        String title_decription_second_wert = ArticlePageObject.getArticleName();

        assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_second_wert );
    }
    @Test
    public void testArticleCheckandBackgroud()
    {

        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String search_line ="Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();

        this.backgroundApp(2);
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"),
                "not_find_article_after_exit_from_background",
                3);
        ArticlePageObject.waitForTitleElement();
    }



}
