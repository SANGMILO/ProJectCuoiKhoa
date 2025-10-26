package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import actions.common.BaseTest;
import common.Log;
import interfaces.elements.WebTablePageInterface;
import org.openqa.selenium.WebDriver;



public class WebTableAction extends BasePage {
    WebDriver driver;
    public WebTableAction(WebDriver driver) {
        this.driver = driver;
    }
    public void Open(){
        Log.info("Open: Elements > Web Tables");
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.goToElements("Web Tables");
    }
    // DQ-WT-001: lấy Email theo First Name
    public String getEmailByFirstName(String firstName) {
        waitForElementPresent(driver, WebTablePageInterface.EMAIL_IN_SAME_ROW, firstName);
        highlightElement(driver, WebTablePageInterface.EMAIL_IN_SAME_ROW, firstName);
        return getTextElement(driver, WebTablePageInterface.EMAIL_IN_SAME_ROW, firstName);
    }
    // DQ-WT-002: click Edit theo Last Name (ancestor) và verify modal mở
    public void clickEditByLastName(String lastName) {
        waitForElementPresent(driver, WebTablePageInterface.EDIT_BTN_IN_ROW_BY_LASTNAME, lastName);
        highlightElement(driver, WebTablePageInterface.EDIT_BTN_IN_ROW_BY_LASTNAME, lastName);
        safeClick(driver, WebTablePageInterface.EDIT_BTN_IN_ROW_BY_LASTNAME, lastName);
        waitForElementPresent(driver, WebTablePageInterface.EDIT_MODAL);
    }


    // DQ-WT-003: đếm các hàng following sau Age=xx /
    public int countFollowingRowsFromAge(String age) {
        waitForElementPresent(driver, WebTablePageInterface.ROW_BY_AGE, age);
//
        waitForElementPresent(driver, WebTablePageInterface.FOLLOWING_ROWS_FROM_AGE, age);
        return getListElementSize(driver, WebTablePageInterface.FOLLOWING_ROWS_FROM_AGE, age);
    }

}
