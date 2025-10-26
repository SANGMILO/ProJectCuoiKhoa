package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.UploadDownloadPageInterface;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UploadDownloadAction extends BasePage {
    private final WebDriver driver;

    public UploadDownloadAction(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        Log.info("Open: Elements > Upload and Download");
        new HomePageAction(driver).goToElements("Upload and Download");
    }

    public String upload(String relativePathFromProjectRoot) {
        Path filePath = resolveFile(relativePathFromProjectRoot);

        Log.info("Upload file: " + filePath);
        waitForElementIsVisible(driver, UploadDownloadPageInterface.INPUT_UPLOAD);
        enterTextToElement(driver, UploadDownloadPageInterface.INPUT_UPLOAD, filePath.toString());

        waitForElementPresent(driver, UploadDownloadPageInterface.TXT_RESULT);
        highlightElement(driver, UploadDownloadPageInterface.TXT_RESULT);
        return getTextElement(driver, UploadDownloadPageInterface.TXT_RESULT);
    }

    private Path resolveFile(String relPath) {
        Path p = Paths.get(relPath).toAbsolutePath().normalize();
        if (!Files.exists(p)) {
            throw new IllegalArgumentException("File không tồn tại: " + p);
        }
        return p;
    }
}
