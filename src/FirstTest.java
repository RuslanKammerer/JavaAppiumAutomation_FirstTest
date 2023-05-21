import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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

}
