package actions.elements;

import actions.common.BasePage;
import common.Log;
import interfaces.CommonInterface;
import interfaces.elements.RadioButtonPageInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static interfaces.elements.RadioButtonPageInterface.*;

public class RadioButtonAction extends BasePage {
    WebDriver driver;
    public RadioButtonAction(WebDriver driver) {
        this.driver = driver;
    }
    public void openRadioButtonPage() {
        Log.info("Open: https://demoqa.com/radio-button");
        getPageUrl(driver, "https://demoqa.com/radio-button");
        waitForPageLoaded(driver);
    }
    public boolean isNoDisabled() {
        Log.info("Check button No co bat khong!");
        waitForElementPresent(driver, RadioButtonPageInterface.NO_INPUT);
        WebElement input = driver.findElement(getXpath(NO_INPUT));

        String disabledAttr = input.getAttribute("disabled"); // null nếu không có
        boolean enabled = input.isEnabled();                  // false nếu disabled/hidden-for-interaction

        boolean isDisabled = (disabledAttr != null) || !enabled;
        Log.info("'No' disabled = " + isDisabled);
        return isDisabled;
    }
}
