package actions.elements;

import actions.HomePageAction;
import actions.common.BasePage;
import common.Log;
import interfaces.elements.UploadDownloadPageInterface;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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

    /**
     * Tải file upload từ classpath hoặc filesystem.
     * Nếu không tìm thấy và chỉ là tên file (vd "logo.png") thì sẽ tự tạo file PNG mẫu tại C:\temp\logo.png (Windows)
     * hoặc thư mục tạm của JVM (Linux/CI).
     */
    public String uploadFromResources(String resourcePath) {
        try {
            Path fileToUpload = resolveUploadFile(resourcePath);

            Log.info("Sử dụng file upload: " + fileToUpload.toAbsolutePath());
            waitForElementPresent(driver, FILE_INPUT);
            driver.findElement(getXpath(FILE_INPUT)).sendKeys(fileToUpload.toAbsolutePath().toString());

            waitForElementPresent(driver, RESULT_TEXT);
            String shown = getTextElement(driver, RESULT_TEXT);
            Log.info("Đường dẫn hiển thị sau upload: " + shown);
            return shown;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi chuẩn bị/đưa file upload: " + e.getMessage(), e);
        }
    }

    // ========================= helpers =========================
    private Path resolveUploadFile(String resourcePath) throws Exception {
        // 1) Thử lấy từ classpath
        Log.info("Thử load từ classpath: " + resourcePath);
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            if (in != null) {
                Path temp = Files.createTempFile("upload_", "_" + resourcePath.replace("/", "_"));
                Files.copy(in, temp, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                Log.info("Đã copy resource ra file tạm: " + temp.toAbsolutePath());
                return temp;
            }
        }

        // 2) Thử dùng đường dẫn như đã truyền vào (absolute/relative)
        Path p1 = Paths.get(resourcePath);
        if (Files.exists(p1)) return p1;

        // 3) Thử ${user.dir}/<path>
        String userDir = System.getProperty("user.dir");
        Path p2 = Paths.get(userDir, resourcePath);
        if (Files.exists(p2)) return p2;

        // 4) Thử ${user.dir}/src/test/resources/<path>
        Path p3 = Paths.get(userDir, "src", "test", "resources", resourcePath);
        if (Files.exists(p3)) return p3;

        // 5) Nếu chỉ là tên file -> tự tạo fixture PNG
        if (!resourcePath.contains("/") && !resourcePath.contains("\\")) {
            Path fixture = autoCreatePngFixture(resourcePath);
            if (Files.exists(fixture)) return fixture;
        }

        throw new RuntimeException("Không tìm thấy file ở classpath hoặc filesystem: " + resourcePath);
    }

    // Tạo file PNG mẫu (64x64)
    private Path autoCreatePngFixture(String fileName) throws Exception {
        boolean isWindows = System.getProperty("os.name", "").toLowerCase().contains("win");
        Path dir = isWindows ? Paths.get("C:", "temp") : Paths.get(System.getProperty("java.io.tmpdir"));
        if (!Files.exists(dir)) Files.createDirectories(dir);

        Path file = dir.resolve(fileName);
        if (!Files.exists(file)) {
            BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setColor(Color.PINK);
            g.fillRect(0, 0, 64, 64);
            g.setColor(Color.BLACK);
            g.drawString("logo", 18, 36);
            g.dispose();
            ImageIO.write(img, "png", file.toFile());
            Log.info("Đã tạo file PNG mẫu: " + file.toAbsolutePath());
        }
        return file;
    }
}
