package actions.common;

import common.GlobalVariables;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * BasePage - Class cơ sở cho toàn bộ Page Object
 * Chứa toàn bộ hàm xử lý Locator, Element, Actions, Alert, Frame, Dropdown, Tab...
 */
public class BasePage {

    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    // Constructor
    //1. getXpath
    public By getXpath(String xpath) {
        return By.xpath(xpath);
    }

    //2. getDynamicXpath
    public By getDynamicXpath(String pattern, String... params) {
        return By.xpath(String.format(pattern, (Object[]) params));
    }

    //3. getElement
    public WebElement getElement(WebDriver driver, String xpath) {
        return driver.findElement(getXpath(xpath));
    }

    //4. getElements
    public List<WebElement> getElements(WebDriver driver, String xpath) {
        return driver.findElements(getXpath(xpath));
    }

    //5. getElements (params)
    public List<WebElement> getElements(WebDriver driver, String xpath, String... params) {
        return driver.findElements(getDynamicXpath(xpath, params));
    }

    //6. getDynamicLocator
    public String getDynamicLocator(String pattern, String... params) {
        return String.format(pattern, (Object[]) params);
    }


    //7. getDynamicElement
    public WebElement getDynamicElement(WebDriver driver, String xpath, String... params) {
        return driver.findElement(getDynamicXpath(xpath, params));
    }

    //8. clickToElement
    public void clickToElement(WebDriver driver, String xpath) {
        waitForElementClickable(driver, xpath);
        getElement(driver, xpath).click();
    }

    //9. clickToElement (params)
    public void clickToElement(WebDriver driver, String xpath, String... params) {
        waitForElementClickable(driver, xpath, params);
        getDynamicElement(driver, xpath, params).click();
    }

    //10. enterTextToElement
    public void enterTextToElement(WebDriver driver, String xpath, String value) {
        waitForElementIsVisible(driver, xpath);
        getElement(driver, xpath).clear();
        getElement(driver, xpath).sendKeys(value);
    }

    //11. enterTextToElement (params)
    public void enterTextToElement(WebDriver driver, String xpath, String value, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        getDynamicElement(driver, xpath, params).clear();
        getDynamicElement(driver, xpath, params).sendKeys(value);
    }

    //12. enterTextToElementUsingActions
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        getElement(driver, xpath).clear();

        actions.sendKeys(getElement(driver, xpath), value).perform();
    }

    //13. enterTextToElementUsingActions (params)
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath, params);
        getDynamicElement(driver, xpath).clear();
        actions.sendKeys(getDynamicElement(driver, xpath, params), value).perform();
    }

    //14. clickToElementByJS
    public void clickToElementByJS(WebDriver driver, String xpath) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, xpath));
    }

    //14. clickToElementByJS
    public void clickToElementByJS(WebDriver driver, String xpath, String... params) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getDynamicElement(driver, xpath, params));
    }


    //15. waitForElementIsVisible
    public void waitForElementIsVisible(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(getElement(driver, xpath)));
    }

    //16. waitForElementIsVisible (params)
    public void waitForElementIsVisible(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(getDynamicElement(driver, xpath, params)));
    }

    //17. waitForElementClickable
    public void waitForElementClickable(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getElement(driver, xpath)));
    }

    //18. waitForElementClickable (params)
    public void waitForElementClickable(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getDynamicElement(driver, xpath, params)));
    }

    //19. highLightElement
    public void highlightElement(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getElement(driver, xpath));
    }

    //20. highLightElement (params)
    public void highlightElement(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getDynamicElement(driver, xpath, params));
    }

    //21. SleepInSecond
    public void SleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }

    //22. hoverToElement
    public void hoverOverElement(WebDriver driver, String xpath) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        actions.moveToElement(getElement(driver, xpath)).perform();
    }

    //23. hoverToElement (params)
    public void hoverOverElement(WebDriver driver, String xpath, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath, params);
        actions.moveToElement(getDynamicElement(driver, xpath)).perform();
    }

    //24. rightClickOnElement
    public void rightClickOnElement(WebDriver driver,String xpath) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        actions.contextClick(getElement(driver,xpath)).perform();
    }

    //25. rightClickOnElement (params)
    public void rightClickOnElement(WebDriver driver,String xpath, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        actions.contextClick(getDynamicElement(driver, xpath, params)).perform();
    }

    //26. doubleClickOnElement
    public void doubleClickOnElement(WebDriver driver,String xpath) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        actions.doubleClick(getElement(driver, xpath)).perform();
    }

    //27. doubleClickOnElement (params)
    public void doubleClickOnElement(WebDriver driver,String xpath, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        actions.doubleClick(getDynamicElement(driver,xpath, params)).perform();
    }

    //28. dragAndDropElement
    public void dragAndDropElement(WebDriver driver, String sourceXpath, String targetXpath) {
        actions = new Actions(driver);
        WebElement source = getElement(driver, sourceXpath);
        WebElement target = getElement(driver, targetXpath);
        actions.dragAndDrop(source, target).perform();
    }


    //29. pressKeyToElement
    public void pressKeyToElement(WebDriver driver, String xpath, Keys key) {
        getElement(driver, xpath).sendKeys(key);
    }

    //30. pressKeyToElement (params)
    public void pressKeyToElement(WebDriver driver,String xpath,Keys key, String... params) {
        getDynamicElement(driver,xpath, params).sendKeys(key);
    }

    //31. getTextElement
    public String getTextElement(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return getElement(driver, xpath).getText().trim();
    }

    //32. getTextElement (params)
    public String getTextElement(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).getText().trim();
    }

    //33. getElementAttributeValue
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute) {
        waitForElementIsVisible(driver, xpath);
        return getElement(driver,xpath).getAttribute(attribute);
    }

    //34. getElementAttributeValue (params)
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).getAttribute(attribute);
    }

    //35. getListElementSize
    public int getListElementSize(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return getElements(driver, xpath).size();
    }

    //36. getListElementSize (params)
    public int getListElementSize(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getElements(driver, xpath, params).size();
    }

    //37. isDisplayElement
    public boolean isDisplayElement(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        try {
            return getElement(driver, xpath).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //38. isDisplayElement (params)
    public boolean isDisplayElement(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        try {
            return getDynamicElement(driver,xpath, params).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //39. isDisplayElements
    public boolean isDisplayElements(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        List<WebElement> list = getElements(driver, xpath);
        return !list.isEmpty() && list.stream().allMatch(WebElement::isDisplayed);
    }

    //40. isDisplayElements (params)
    public boolean isDisplayElements(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        List<WebElement> list = getElements(driver, xpath, params);
        return !list.isEmpty() && list.stream().allMatch(WebElement::isDisplayed);
    }

    //41. getPageUrl
    public void getPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    //42. getPageTitle
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // 43. getPageSourceCode
    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    // 44. getCurrentUrl
    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    // 45. backToPage
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    // 46. forwardToPage
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    // 47. refreshPage
    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // 48. waitForAlertPresence
    public Alert waitForAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // 49. acceptAlert
    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    // 50. cancelAlert
    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    // 51. getTextAlert
    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    // 52. enterTextToAlert
    public void enterTextToAlert(WebDriver driver, String text) {
        waitForAlertPresence(driver).sendKeys(text);
    }

    // 53. switchWindowByID
    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    // 54. switchWindowByTitle
    public void switchWindowByTitle(WebDriver driver, String expectedTitle) {
        for (String id : driver.getWindowHandles()) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(expectedTitle)) {
                return;
            }
        }
    }

    // 55. closeAllWindowsWithoutParent
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        for (String id : driver.getWindowHandles()) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    // 56. selectItemInDefaultDropdown
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String text) {
        waitForElementIsVisible(driver, xpath);
        new Select(getElement(driver, xpath)).selectByVisibleText(text);
    }

    // 57. selectItemInDefaultDropdown (params)
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String text, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        new Select(getDynamicElement(driver, xpath, params)).selectByVisibleText(text);
    }

    // 58. getFirstSelectedTextItem
    public String getFirstSelectedTextItem(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return new Select(getElement(driver, xpath)).getFirstSelectedOption().getText();
    }

    // 59. getFirstSelectedTextItem (params)
    public String getFirstSelectedTextItem(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return new Select(getDynamicElement(driver, xpath, params)).getFirstSelectedOption().getText();
    }

    // 60. isDropdownMultiple
    public boolean isDropdownMultiple(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return new Select(getElement(driver, xpath)).isMultiple();
    }

    // 61. isDropdownMultiple (params)
    public boolean isDropdownMultiple(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return new Select(getDynamicElement(driver, xpath, params)).isMultiple();
    }

    // 62. checkToCheckboxOrRadio
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        WebElement e = getElement(driver, xpath);
        if (!e.isSelected()) {
            e.click();
        }
    }

    // 63. checkToCheckboxOrRadio (params)
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        WebElement e = getDynamicElement(driver, xpath, params);
        if (!e.isSelected()) {
            e.click();
        }
    }

    // 64. unCheckToCheckbox
    public void unCheckToCheckbox(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        WebElement e = getElement(driver, xpath);
        if (e.isSelected()) {
            e.click();
        }
    }

    // 65. unCheckToCheckbox (params)
    public void unCheckToCheckbox(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        WebElement e = getDynamicElement(driver, xpath, params);
        if (e.isSelected()) {
            e.click();
        }
    }

    // 66. setImplicitTime
    public void setImplicitTime(WebDriver driver, long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    // 67. switchToFrame
    public void switchToFrame(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        driver.switchTo().frame(getElement(driver, xpath));
    }

    // 67b. switchToFrame (params)
    public void switchToFrame(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        driver.switchTo().frame(getDynamicElement(driver, xpath, params));
    }

    // 68. switchToDefaultContent
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }


    //    69.ScrollInToView
    public void scrollIntoView(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'center'});", el
            );
        } catch (JavascriptException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        }
    }
    // 70. clickReliable
    public void clickReliable(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        try {
            waitForElementClickable(driver, xpath, params);
            scrollIntoView(driver, xpath, params);
            clickToElement(driver, xpath, params);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getDynamicElement(driver, xpath, params));
        }
    }

    // 71. dismissStickyOverlaysIfAny
    public void dismissStickyOverlaysIfAny(WebDriver driver) {
        try {
            List<WebElement> overlays = driver.findElements(
                    By.xpath("//*[contains(@style,'position: fixed') or contains(@style,'position: absolute')]"));
            for (WebElement overlay : overlays) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
            }
        } catch (Exception ignored) {
        }
    }

    // 72. safeClick
    public void safeClick(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        for (int i = 0; i < 3; i++) {
            try {
                waitForElementClickable(driver, xpath, params);
                getDynamicElement(driver, xpath, params).click();
                return;
            } catch (ElementClickInterceptedException e) {
                dismissStickyOverlaysIfAny(driver);
                SleepInSeconds(1);
            } catch (StaleElementReferenceException e) {
                SleepInSeconds(1);
            }
        }
        clickToElementByJS(driver, xpath, params);
    }

    // 73. scrollToBottom
    public void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // 74. scrollToTop
    public void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    // 75. waitForPageLoaded
    public void waitForPageLoaded(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    // 76. clickToElementByAction
    public void clickToElementByAction(WebDriver driver, String xpath, String... params) {
        waitForElementClickable(driver, xpath, params);
        actions = new Actions(driver);
        actions.moveToElement(getDynamicElement(driver, xpath, params)).click().perform();
    }

    // 77. getElementsText
    public List<String> getElementsText(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        List<String> texts = new ArrayList<>();
        for (WebElement e : getElements(driver, xpath, params)) {
            texts.add(e.getText().trim());
        }
        return texts;
    }
    //78. // chờ element có trong DOM (không cần visible)
    public void waitForElementPresent(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(getDynamicXpath(xpath, params)));
    }

    public void waitForTextPresent(WebDriver driver, String xpath, String expected, String... params) {
        By by = getDynamicXpath(xpath, params);
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.LONG_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElementLocated(by, expected));
    }

}