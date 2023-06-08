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
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Carbon";
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        assertTrue("Не найдено достаточное кол-во страниц", amount_of_search_res>3);

        SearchPageObject.waitForCancelBtnAppear();
        SearchPageObject.cliclCancelBtn();
        SearchPageObject.waitForCancelBtnDissapear();
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
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String seacrh_line = "Java";
        String article_1 = "Java (programming language)";
        String article_2 = "Island in Indonesia";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(seacrh_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);
        ArticlePageObject.waitForTitleElement();
        String article_title_1 = ArticlePageObject.getArticleName();
        String name_of_folder = "Test1";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickByArticleWithSubstring(article_2);
        ArticlePageObject.waitForTitleElement();
        String article_title_2 = ArticlePageObject.getArticleName();

        ArticlePageObject.addArticleToSavedList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUi.clickToMyLists();

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title_1);

        SearchPageObject.clickByArticleWithSubstringInSavedList(article_2);
        String check_article = ArticlePageObject.getArticleName();
        assertEquals("Заголовки статей не совпадают", article_title_2, check_article);


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
    {   SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();

        String search_line ="Java";
        String article_1 = "Java (programming language)";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);

        ArticlePageObject.assertNowElementPresent();
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
