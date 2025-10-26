package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.DatePickerPageInterface;
import org.openqa.selenium.WebDriver;


public class DatePickerAction extends BasePage {
    private final WebDriver driver;

    public DatePickerAction(WebDriver driver) { this.driver = driver; }

    public void open() {
        Log.info("Open: Widgets > Date Picker");
        new HomePageAction(driver).goTo("Widgets", "Date Picker");
    }

    public void pickDay(int day) {
        waitForElementClickable(driver, DatePickerPageInterface.DATE_INPUT);
        safeClick(driver, DatePickerPageInterface.DATE_INPUT);
        String dayStr = String.valueOf(day);
        waitForElementPresent(driver, DatePickerPageInterface.DAY_CELL_BY_NUM, dayStr);
        highlightElement(driver, DatePickerPageInterface.DAY_CELL_BY_NUM, dayStr);
        safeClick(driver, DatePickerPageInterface.DAY_CELL_BY_NUM, dayStr);
    }

    public String getValue() {
        return getElementAttributeValue(driver, DatePickerPageInterface.DATE_INPUT, "value");
    }
}
