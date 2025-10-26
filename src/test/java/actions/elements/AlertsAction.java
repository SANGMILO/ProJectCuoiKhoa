package actions.alerts;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.AlertsPageInterface;
import org.openqa.selenium.WebDriver;



public class AlertsAction extends BasePage {
    private final WebDriver driver;

    public AlertsAction(WebDriver driver) { this.driver = driver; }

    public void open() {
        Log.info("Open: Alerts, Frame & Windows > Alerts");
        new HomePageAction(driver).goTo("Alerts, Frame & Windows", "Alerts");
    }

    public void clickSimpleAlertAndAccept() {
        waitForElementClickable(driver, AlertsPageInterface.ALERT_SIMPLE_BTN);
        highlightElement(driver, AlertsPageInterface.ALERT_SIMPLE_BTN);
        safeClick(driver, AlertsPageInterface.ALERT_SIMPLE_BTN);
        acceptAlert(driver);
        waitForPageLoaded(driver);
    }
}
