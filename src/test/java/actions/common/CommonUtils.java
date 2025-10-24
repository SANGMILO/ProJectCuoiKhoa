package actions.common;

import org.openqa.selenium.WebDriver;

public class CommonUtils {
    WebDriver driver;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }
    public void SleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }
}
