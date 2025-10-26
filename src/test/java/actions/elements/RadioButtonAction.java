package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.RadioButtonPageInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RadioButtonAction extends BasePage {
    private final WebDriver driver;

    public RadioButtonAction(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        Log.info("Opening Radio Button");
        new HomePageAction(driver).goToElements("Radio Button");
    }

    public boolean isNoDisabled() {
        Log.info("Check button No co bat khong!");
        waitForElementPresent(driver, RadioButtonPageInterface.NO_INPUT);
        WebElement input = driver.findElement(getXpath(RadioButtonPageInterface.NO_INPUT));

        String disabledAttr = input.getAttribute("disabled");
        boolean enabled = input.isEnabled();

        boolean isDisabled = (disabledAttr != null) || !enabled;
        Log.info("'No' disabled = " + isDisabled);
        return isDisabled;
    }
}
