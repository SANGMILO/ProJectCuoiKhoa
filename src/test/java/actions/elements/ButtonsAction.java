package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.ButtonsPageInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class ButtonsAction extends BasePage {
    WebDriver driver;
    public ButtonsAction(WebDriver driver){
        this.driver=driver;
    }
    public void open() {
        Log.info("Open: Elements > Buttons");
        new HomePageAction(driver).goToElements("Buttons");
    }
    public String doubleClickAndGetMessage() {
        waitForElementClickable(driver, ButtonsPageInterface.BTN_DOUBLE);
        scrollIntoView(driver, ButtonsPageInterface.BTN_DOUBLE);
        highlightElement(driver, ButtonsPageInterface.BTN_DOUBLE);

        WebElement btn = getElement(driver, ButtonsPageInterface.BTN_DOUBLE);
        new Actions(driver).moveToElement(btn).doubleClick(btn).perform();
        waitForElementIsVisible(driver, ButtonsPageInterface.MSG_DOUBLE);
        highlightElement(driver, ButtonsPageInterface.MSG_DOUBLE);
        return getTextElement(driver, ButtonsPageInterface.MSG_DOUBLE);
    }
}
