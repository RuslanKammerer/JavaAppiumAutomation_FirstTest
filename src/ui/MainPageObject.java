package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(By by, String err_msg, long time_sec)
    {
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(By by, String err_msg)

    {
        return waitForElementPresent(by, err_msg, 3);
    }

    public WebElement waitForElementandClick(By by, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.click();
        return element;
    }
    public WebElement waitForElementandSendKeys(By by, String value, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(By by, String err_msg, long time_sec)
    {
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementAndClear(By by, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(by,err_msg,time_sec);
        element.clear();
        return element;
    }
    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }
    public void swipeUpQuick()
    {
        swipeUp(250);

    }
    public void swipeUpToFindElement(By by, String err_msg, int max_swipes)
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
    public void swipeElemntToLeft(By by, String err_msg)
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
    public int getAmountofElement(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementNotPresent(By by, String err_msg)
    {
        int amount_of_el = getAmountofElement(by);
        if (amount_of_el>0) {
            String def_msg = "An element '" + by.toString() + "'supposed not to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }
    }
    public void assertElementPresent(By by, String err_msg)
    {
        int amount_of_el = getAmountofElement(by);
        if (amount_of_el==0)
        {
            String def_msg = "An element '" + by.toString() + "'supposed to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }

    }
    public String waitForElementAndGetAtribute(By by, String attriibute, String err_msg, long timeInsec)
    {
        WebElement element = waitForElementPresent(by, err_msg, timeInsec);
        return element.getAttribute(attriibute);
    }
}
