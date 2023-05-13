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
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        WebElement element_search;
        element_search = waitForElementPresentByXpath("//*[contains(@text,'Search Wikipedia')]", "Элемент не найден", 2);
        element_search.click();
        Assert.assertTrue(assertElementHasText("org.wikipedia:id/search_src_text","Search Wikipedia","Текст не найден"));

    }
    private boolean assertElementHasText(String locator, String exp_text, String err_msg) throws Exception {
        WebElement srch = driver.findElementById(locator);
        boolean res = driver.findElementById(locator).getText().contains(exp_text);
        if (res == true) {
            return res;
        }
        else {
            throw new Exception(err_msg);
        }
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_msg, long time_out_in_sec)
    {
        WebDriverWait wait = new WebDriverWait(driver, time_out_in_sec);
        wait.withMessage(error_msg + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_msg)
    {
        return waitForElementPresentByXpath(xpath, error_msg, 5);
    }
}
