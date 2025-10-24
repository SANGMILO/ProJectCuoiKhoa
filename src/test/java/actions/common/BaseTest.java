package actions.common;

import common.GlobalVariables;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    private static WebDriver driver;
    ChromeOptions chromeOptions;
    EdgeOptions edgeOptions;

    public WebDriver getBrowserDriver(String browserName, String url) {
        String browser = browserName.toUpperCase();
        switch (browser) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "HCHROME":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                throw new RuntimeException("Please enter correct browser name");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariables.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

}