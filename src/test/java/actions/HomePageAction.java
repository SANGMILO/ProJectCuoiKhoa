package actions;

import actions.common.BasePage;
import common.Log;
import interfaces.CommonInterface;
import org.openqa.selenium.WebDriver;

public class HomePageAction extends BasePage {
    private final WebDriver driver;

    public HomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    // Kiểm tra xem đang ở trang chủ hay chưa
    private boolean isOnHome() {
        return isDisplayElement(driver, CommonInterface.HOME_PAGE_MENU, "Elements");
    }

    // Nếu chưa ở Home thì điều hướng về trang chủ
    private void ensureHome() {
        if (!isOnHome()) {
            Log.info("Không ở trang chủ, điều hướng về Home...");
            getPageUrl(driver, "https://demoqa.com/");
            waitForPageLoaded(driver);
        }
    }

    public void clickCard(String cardText) {
        Log.info("Clicking on card: " + cardText);
        waitForPageLoaded(driver);
        waitForElementClickable(driver, CommonInterface.HOME_PAGE_MENU, cardText);
        scrollIntoView(driver, CommonInterface.HOME_PAGE_MENU, cardText);
        highlightElement(driver, CommonInterface.HOME_PAGE_MENU, cardText);
        safeClick(driver, CommonInterface.HOME_PAGE_MENU, cardText);
        waitForPageLoaded(driver);
    }

    public void clickLeftMenu(String menuText) {
        Log.info("Clicking on left menu: " + menuText);
        waitForElementPresent(driver, CommonInterface.ELEMENTS_MENU, menuText);
        scrollIntoView(driver, CommonInterface.ELEMENTS_MENU, menuText);
        highlightElement(driver, CommonInterface.ELEMENTS_MENU, menuText);
        safeClick(driver, CommonInterface.ELEMENTS_MENU, menuText);
        waitForPageLoaded(driver);
    }

    public void goTo(String cardText, String leftMenuText) {
        ensureHome();
        clickCard(cardText);
        clickLeftMenu(leftMenuText);
    }

    public void goToElements(String leftMenuText) {
        goTo("Elements", leftMenuText);
    }
}
