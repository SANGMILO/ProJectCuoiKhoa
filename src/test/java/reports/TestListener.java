package reports;

import actions.common.BaseTest;
import com.aventstack.extentreports.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName())
                .assignCategory(result.getTestContext().getSuite().getName());
        ExtentTestManager.setTest(test);
    }

    // ======================
    // ✅ PASS: Chụp ảnh khi test pass
    // ======================
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        test.log(Status.PASS, "✅ Test passed: " + result.getMethod().getMethodName());
        attachScreenshot(test, "Screenshot on success");
        ExtentTestManager.remove();
    }

    // ======================
    // ❌ FAIL: Chụp ảnh khi test fail
    // ======================
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        test.log(Status.FAIL, "❌ Test failed: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        }
        attachScreenshot(test, "Screenshot on failure");
        ExtentTestManager.remove();
    }

    // ======================
    // ⚠️ SKIP: Chụp ảnh khi test bị bỏ qua
    // ======================
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        String reason = (result.getThrowable() != null)
                ? result.getThrowable().getMessage()
                : "No skip reason provided.";
        test.log(Status.SKIP, "⚠️ Test skipped: " + reason);
        attachScreenshot(test, "Screenshot on skip");
        ExtentTestManager.remove();
    }

    // ======================
    // 📸 HÀM DÙNG CHUNG ĐỂ GẮN ẢNH VÀO REPORT
    // ======================
    private void attachScreenshot(ExtentTest test, String title) {
        try {
            if (BaseTest.getDriver() != null) {
                String base64 = ((TakesScreenshot) BaseTest.getDriver())
                        .getScreenshotAs(OutputType.BASE64);
                test.info(title, MediaEntityBuilder
                        .createScreenCaptureFromBase64String(base64)
                        .build());
            } else {
                test.warning("⚠️ Driver null — không thể chụp ảnh.");
            }
        } catch (Exception e) {
            test.warning("⚠️ Lỗi khi chụp ảnh: " + e.getMessage());
        }
    }
}
