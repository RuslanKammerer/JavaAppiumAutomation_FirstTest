package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private static final String
            NAV_TAB_READING_LISTS = "org.wikipedia:id/nav_tab_reading_lists";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void clickToMyLists()
    {
        this.waitForElementandClick(By.id(NAV_TAB_READING_LISTS),
                "not find navigation button",
                2);
    }

}
