package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import org.openqa.selenium.WebDriver;

import static interfaces.elements.LinksPageInterface.*;

public class LinksAction extends BasePage {
    private final WebDriver driver;

    public LinksAction(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        Log.info("Open: Elements > Links");
        new HomePageAction(driver).goToElements("Links");
        waitForPageLoaded(driver);
    }

    public String clickCreatedAndWaitResponse() {
        waitForElementClickable(driver, LNK_CREATED);
        scrollIntoView(driver, LNK_CREATED);
        highlightElement(driver, LNK_CREATED);
        clickToElement(driver, LNK_CREATED);

        waitForTextPresent(driver, TXT_RESPONSE, "201");
        highlightElement(driver, TXT_RESPONSE);
        return getTextElement(driver, TXT_RESPONSE);
    }
}
