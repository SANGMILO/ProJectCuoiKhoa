package actions.forms;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.PracticeFormPageInterface;
import org.openqa.selenium.WebDriver;


public class PracticeFormAction extends BasePage {
    private final WebDriver driver;

    public PracticeFormAction(WebDriver driver) { this.driver = driver; }

    public void open() {
        Log.info("Open: Forms > Practice Form");
        new HomePageAction(driver).goTo("Forms", "Practice Form");
    }

    public void fillMinimumValid(String first, String last, String email, String gender, String mobile) {
        waitForElementPresent(driver, PracticeFormPageInterface.FIRST_NAME);
        enterTextToElement(driver, PracticeFormPageInterface.FIRST_NAME, first);
        enterTextToElement(driver, PracticeFormPageInterface.LAST_NAME, last);
        enterTextToElement(driver, PracticeFormPageInterface.EMAIL, email);

        safeClick(driver, PracticeFormPageInterface.GENDER_MALE_LABEL);

        enterTextToElement(driver, PracticeFormPageInterface.MOBILE, mobile);
    }

    public void submit() {
        scrollIntoView(driver, PracticeFormPageInterface.SUBMIT);
        safeClick(driver, PracticeFormPageInterface.SUBMIT);
        waitForElementPresent(driver, PracticeFormPageInterface.MODAL_TITLE);
    }

    public boolean isModalShown() {
        return isDisplayElement(driver, PracticeFormPageInterface.MODAL_TITLE);
    }

    public String getResultValue(String label) {
        waitForElementPresent(driver, PracticeFormPageInterface.RESULT_CELL_BY_LABEL, label);
        return getTextElement(driver, PracticeFormPageInterface.RESULT_CELL_BY_LABEL, label);
    }
}
