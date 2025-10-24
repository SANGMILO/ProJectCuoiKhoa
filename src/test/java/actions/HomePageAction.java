package actions;

import actions.common.BasePage;
import actions.common.BaseTest;
import common.Log;
import interfaces.CommonInterface;
import org.openqa.selenium.WebDriver;

public class HomePageAction extends BasePage {
    WebDriver driver;

    public HomePageAction(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnMenu(String menu) {
        Log.info("Di toi module: " + menu);
        waitForElementClickable(driver, CommonInterface.HOME_PAGE_MENU, menu);
        scrollIntoView(driver, CommonInterface.HOME_PAGE_MENU, menu);
        highlightElement(driver, CommonInterface.HOME_PAGE_MENU, menu);
        clickToElement(driver, CommonInterface.HOME_PAGE_MENU, menu);
//        safeClick(driver, CommonInterface.HOME_PAGE_MENU, menu);
//        waitForPageLoaded(driver);
    }
}
