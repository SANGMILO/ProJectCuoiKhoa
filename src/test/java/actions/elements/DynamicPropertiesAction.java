package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.DynamicPropertiesPageInterface;
import org.openqa.selenium.WebDriver;


public class DynamicPropertiesAction extends BasePage {
    private final WebDriver driver;

    public DynamicPropertiesAction(WebDriver driver) { this.driver = driver; }

    public void open() {
        Log.info("Open: Elements > Dynamic Properties");
        new HomePageAction(driver).goToElements("Dynamic Properties");
    }

    public boolean waitUntilEnableAfterEnabled(long seconds) {
        waitForElementPresent(driver, DynamicPropertiesPageInterface.ENABLE_AFTER_BTN);
        setImplicitTime(driver, 0);
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < seconds * 1000L) {
                if (getElement(driver, DynamicPropertiesPageInterface.ENABLE_AFTER_BTN).isEnabled()) return true;
                SleepInSeconds(1);
            }
            return false;
        } finally {
        }
    }
}
