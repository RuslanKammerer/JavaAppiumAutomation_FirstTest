import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and8");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        //capabilities.setCapability("app","C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation_FirstTest\\apks\\org.wikipedia_50437_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void FirstTest() throws Exception {
        WebElement skip_btn = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        skip_btn.click();
        waitForElementandClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не удалось найти", 1);
//        WebElement src_field = waitForElementPresentByXpath("//*[contains(@text,'Search Wikipedia')]", "Cannot_find");
//        src_field.click();
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не удалось найти java", 1);
        //WebElement src_line = driver.findElementById("org.wikipedia:id/search_src_text");
        //src_line.sendKeys("Java");
       waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"), "not_find", 3);
    }
    @Test
    public void testCancelSearch()
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не удалось найти java", 1);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "не удалось очистить элемент", 2);
        //waitForElementandClick(By.id("org.wikipedia:id/search_close_btn"), "не удалось найти id кнопки отмены поиска", 2);
        //waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "кнопка еще на месте", 2);
        waitForElementPresent(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "не удалось найти поле", 2);
    }
    @Test
    public void testCompareArticleTitle()
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не удалось найти java", 1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"), "not_find", 3);
        WebElement title_name = waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "Не удалось найти заголовок статьи", 5);
        String article_title = title_name.getAttribute("text");
        Assert.assertEquals("Title is not match", "Java (programming language)", article_title);

    }
    @Test
    public void testSwipeArticle()
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Appium", "Не удалось найти java", 1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"), "not_find", 3);
        waitForElementPresent(By.xpath("//*[contains(@text,'Automation for Apps')]"), "Не удалось найти заголовок статьи", 5);
        swipeUpToFindElement(By.xpath("//*[contains(@text,'View article in browser')]"), "Не удалось свайпом найти свайпом что-то", 20);
    }
    @Test
    public void findArticlesandClose()
            // В данном тест-кейсе ищется 3 статьи по слову Carbon, стирается строка пропуска и проверяется, что в поле выдачи пусто
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Carbon", "Не удалось отправить значение для поиска", 1);
        waitForElementPresent(By.xpath("//*[contains(@text,'Carbon dioxide')]"), "Не удалось найти элемент по названию", 2);
        waitForElementPresent(By.xpath("//*[contains(@text,'Carbon nanotube')]"), "Не удалось найти элемент по названию", 2);
        waitForElementPresent(By.xpath("//*[contains(@text,'Carbohydrate')]"), "Не удалось найти элемент по названию", 2);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "не удалось очистить элемент", 2);
        waitForElementPresent(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "не удалось найти поле поиска", 2);
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Carbon dioxide')]"), "Не удалось найти элемент по названию", 2);
    }
    @Test
    public void saveFirstArticleToList()
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "не найдена кнопка",
                1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"),
                "не удалось найти id поля поиска",
                2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Не удалось найти java",
                1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"),
                "not_find",
                3);

        waitForElementandClick(By.id("org.wikipedia:id/page_save"),
                "not find save button",
                2);
        waitForElementandClick(By.id("org.wikipedia:id/snackbar_action"),
                "not find save button",
                2);

        String name_of_folder = "Test1";

        waitForElementandSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder,
                "Не удалось ввести имя папки для сохранения",
                1);
        waitForElementandClick(By.id("android:id/button1"),
                "not find OK button",
                2);
        waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        waitForElementandClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "not find navigation button",
                2);
        waitForElementandClick(By.xpath("//*[contains(@text,'"+name_of_folder+"')]"),
                "Не удалось найти сохраненный список статей",
                1);
        swipeElemntToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Не удалось найти статью для удаления");

        waitForElementNotPresent(By.xpath("//*[contains(@text,'You have no articles added to this list.')]"),
                "Список статей не пуст",
                2);
    }
    @Test
    public void testSaveAndDeleteArticles()
    {
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Не найдена кнопка пропуска интро",
                1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"),
                "не удалось найти id поля поиска статей",
                2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Не удалось ввести значения для поиска статьи",
                1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"),
                "Не удалось найти статью",
                3);
        WebElement title_name = waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти заголовок статьи",
                5);

        String article_name = title_name.getAttribute("text");

        waitForElementandClick(By.id("org.wikipedia:id/page_save"),
                "Не найдена кнопка для сохранения статьи",
                2);
        waitForElementandClick(By.id("org.wikipedia:id/snackbar_action"),
                "Не найдена кнопка во всплывающем снек-баре",
                2);

        String name_of_folder = "Test1";

        waitForElementandSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder,
                "Не удалось ввести имя папки для сохранения",
                1);
        waitForElementandClick(By.id("android:id/button1"),
                "Не найдена кнопка ОК в диалоге",
                2);
        waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти в список статей",
                1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island in Indonesia']"),
                "Не удалось найти статью",
                3);
        waitForElementandClick(By.id("org.wikipedia:id/page_save"),
                "Не найдена кнопка для сохранения статьи",
                2);
        waitForElementandClick(By.id("org.wikipedia:id/snackbar_action"),
                "Не найдена кнопка во всплывающем снек-баре",
                2);
        waitForElementandClick(By.xpath("//*[contains(@text,'"+name_of_folder+"')]"),
                "Не удалось найти сохраненный список статей",
                1);
        waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        waitForElementandClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не удалось выйти назад",
                1);
        waitForElementandClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Не найдена кнопка навигации для сохраненных статей",
                2);
        waitForElementandClick(By.xpath("//*[contains(@text,'"+name_of_folder+"')]"),
                "Не удалось найти сохраненный список статей",
                1);
        swipeElemntToLeft(By.xpath("//*[@text='Island in Indonesia']"),
                "Не удалось найти статью для удаления");
        waitForElementNotPresent(By.xpath("//*[contains(@text,'You have no articles added to this list.')]"),
                "Список статей пустой",
                2);
        waitForElementandClick(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти статью в сохраненном списке статей",
                3);
        WebElement title_name_check = waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Не удалось найти заголовок сохраненной статьи",
                5);
        String article_name_check = title_name_check.getAttribute("text");
        Assert.assertEquals("Искомый заголовок статьи не совпадает с сохраненным",article_name,article_name_check);
    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        String search_line ="Linkin park discography";
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), search_line, "Не удалось отправить значение для поиска", 1);
        String search_res_loc = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(search_res_loc),"Cannot find anything by this request" + search_line, 3);
        int amount_of_seacrh_res = getAmoutofElement(By.xpath(search_res_loc));
        Assert.assertTrue("Found too many elements",amount_of_seacrh_res>0);
    }
    @Test
    public void testAmountofEmptySearch()
    {
        String search_line = "blablablablabla";
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), search_line, "Не удалось отправить значение для поиска", 1);
        String search_res_loc = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_res_label="//*[contains(@text, 'No results')]";
        waitForElementPresent(By.xpath(empty_res_label), "Page is not empty by req " + search_line, 2);
        assertElementNotPresent(By.xpath(search_res_loc), "We found some result by req" + search_line);

    }
    @Test
    public void testTitlePage()
    {
        String search_line ="Java";
        waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Не найдена кнопка skip", 1);
        waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), search_line, "Не удалось отправить значение для поиска", 1);
        waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"), "Не удалось кликнуть на нужную статью", 2);
        String target_element = "//*[@resource-id='pcs-edit-section-title-description']";
        //waitForElementPresent(By.xpath(target_element), "элемент не найден");
        assertElementPresent(By.xpath(target_element), "Нужный элемент не найден на странице");
    }
    private WebElement waitForElementPresent(By by, String err_msg, long time_sec)
    {
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String err_msg)

    {
        return waitForElementPresent(by, err_msg, 3);
    }

    private  WebElement waitForElementandClick(By by, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.click();
        return element;
    }
    private  WebElement waitForElementandSendKeys(By by, String value, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String err_msg, long time_sec)
    {
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private WebElement waitForElementAndClear(By by, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.clear();
        return element;
    }
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }
    protected void swipeUpQuick()
    {
        swipeUp(200);

    }
    protected void swipeUpToFindElement(By by, String err_msg, int max_swipes)
    {
        int already_swipes = 0;
        while (driver.findElements(by).size()==0)
        {
            if (already_swipes > max_swipes) {
                waitForElementPresent(by, "Не удалось найти элемент по свайпу.\n"+err_msg,0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }
    protected void swipeElemntToLeft(By by, String err_msg)
    {
        WebElement element = waitForElementPresent(by, err_msg, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(300).moveTo(left_x,middle_y).release().perform();

    }
    private int getAmoutofElement(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }
    private void assertElementNotPresent(By by, String err_msg)
    {
        int amount_of_el = getAmoutofElement(by);
        if (amount_of_el>0) {
            String def_msg = "An element '" + by.toString() + "'supposed not to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }
    }
    private void assertElementPresent(By by, String err_msg)
    {
        int amount_of_el = getAmoutofElement(by);
        if (amount_of_el==0)
        {
            String def_msg = "An element '" + by.toString() + "'supposed to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }

    }

}
