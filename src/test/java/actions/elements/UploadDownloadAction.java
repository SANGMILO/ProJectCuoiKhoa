package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.UploadDownloadPageInterface;
import org.openqa.selenium.WebDriver;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static interfaces.elements.UploadDownloadPageInterface.*;

public class UploadDownloadAction extends BasePage {
    private final WebDriver driver;

    public UploadDownloadAction(WebDriver driver) {
        this.driver = driver;
    }

    public UploadDownloadAction open() {
        Log.info("Open: Elements > Upload and Download");
        new HomePageAction(driver).goToElements("Upload and Download");
        return this;
    }

    // Upload 1 file trong thư mục src/test/resources và trả về text hiển thị sau upload
    public String uploadFromResources(String resourcePath) {
        Log.info("Thử load file upload: " + resourcePath);
        java.net.URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(resourcePath);

        java.nio.file.Path fileToUpload = null;

        try {
            if (url != null) {
                // Copy resource classpath -> file tạm
                java.nio.file.Path temp = java.nio.file.Files.createTempFile("upload_", "_" + resourcePath.replace("/", "_"));
                try (java.io.InputStream in = url.openStream()) {
                    java.nio.file.Files.copy(in, temp, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
                fileToUpload = temp;
                Log.info("Đã lấy từ classpath và copy ra file tạm: " + fileToUpload.toAbsolutePath());
            } else {
                Log.warn("Không thấy trên classpath: " + resourcePath + " -> fallback qua filesystem");

                java.nio.file.Path p1 = java.nio.file.Paths.get(resourcePath);
                if (!java.nio.file.Files.exists(p1)) {
                    String userDir = System.getProperty("user.dir");
                    java.nio.file.Path p2 = java.nio.file.Paths.get(userDir, resourcePath);
                    if (!java.nio.file.Files.exists(p2)) {
                        java.nio.file.Path p3 = java.nio.file.Paths.get(userDir, "src", "test", "resources", resourcePath);
                        if (java.nio.file.Files.exists(p3)) {
                            fileToUpload = p3;
                        }
                    } else {
                        fileToUpload = p2;
                    }
                } else {
                    fileToUpload = p1;
                }

                if (fileToUpload == null || !java.nio.file.Files.exists(fileToUpload)) {
                    throw new RuntimeException("Không tìm thấy file để upload ở bất kỳ nơi nào: " + resourcePath);
                }
                Log.info("Dùng trực tiếp file trên filesystem: " + fileToUpload.toAbsolutePath());
            }
        } catch (Exception e) {
            throw new RuntimeException(" Lỗi khi chuẩn bị file upload: " + e.getMessage(), e);
        }

        waitForElementPresent(driver, UploadDownloadPageInterface.FILE_INPUT);
        driver.findElement(getXpath(UploadDownloadPageInterface.FILE_INPUT))
                .sendKeys(fileToUpload.toAbsolutePath().toString());

        waitForElementPresent(driver, UploadDownloadPageInterface.RESULT_TEXT);
        String shown = getTextElement(driver, UploadDownloadPageInterface.RESULT_TEXT);
        Log.info("Đường dẫn hiển thị sau upload: " + shown);
        return shown;
    }
}
