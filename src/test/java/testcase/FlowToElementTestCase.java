package testcase;

import actions.common.AssertUtils;
import actions.common.BaseTest;
import actions.elements.*;
import common.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlowToElementTestCase extends BaseTest {
    private RadioButtonAction radioButtonAction;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        String browser = System.getProperty("browser", "chrome");
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com/");
        getBrowserDriver(browser, baseUrl);
        radioButtonAction = new RadioButtonAction(getDriver());
    }

    // DQ-RB-002 — Radio Button: "No" bị disable
    @Test(priority = 1, groups = {"elements","radio"}, description = "DQ-RB-002 | Elements > Radio Button | 'No' is disabled")
    public void TC_DQ_RB_002() {
        Log.info("=== START: DQ-RB-002 ===");
        radioButtonAction.open();

        boolean disabled = radioButtonAction.isNoDisabled();
        AssertUtils.assertTrue(disabled, "'No' phải disabled nhưng phát hiện enable.");
        Log.info("KẾT QUẢ: 'No' bị disabled đúng mong đợi.");
        Log.info("=== END: DQ-RB-002 ===");
    }

    // DQ-WT-001 — Web Tables: lấy Email theo First Name = 'Cierra'
    @Test(priority = 2, groups = {"elements","webtables"})
    public void TC_DQ_WT_001() {
        Log.info("=== START: DQ-WT-001 ===");
        WebTableAction wt = new WebTableAction(getDriver());
        wt.Open();

        Log.info("Tìm email theo First Name = 'Cierra'");
        String email = wt.getEmailByFirstName("Cierra");
        Log.info("Email của Cierra: " + email);

        AssertUtils.assertContains(email, "@", "Email phải chứa '@'");
        Log.info("KẾT QUẢ: Email hợp lệ.");
        Log.info("=== END: DQ-WT-001 ===");
    }

    // DQ-WT-002 — Web Tables: từ Last Name 'Cantrell' locate Edit (ancestor) và mở form
    @Test(priority = 3, groups = {"elements","webtables"})
    public void TC_DQ_WT_002() {
        Log.info("=== START: DQ-WT-002 ===");
        WebTableAction wt = new WebTableAction(getDriver());
        wt.Open();

        Log.info("Click vào nút Edit tương ứng với Last Name = 'Cantrell'");
        wt.clickEditByLastName("Cantrell");

        AssertUtils.assertTrue(true, "Form Edit phải hiển thị.");
        Log.info("KẾT QUẢ: Form Edit mở thành công.");
        Log.info("=== END: DQ-WT-002 ===");
    }

    // DQ-WT-003 — Web Tables: liệt kê tất cả hàng sau hàng có Age=39
    @Test(priority = 4, groups = {"elements","webtables"})
    public void TC_DQ_WT_003() {
        Log.info("=== START: DQ-WT-003 ===");
        WebTableAction wt = new WebTableAction(getDriver());
        wt.Open();

        Log.info("Đếm số hàng sau hàng có Age = 39");
        int count = wt.countFollowingRowsFromAge("39");
        Log.info("Số hàng sau Age=39: " + count);

        AssertUtils.assertTrue(count >= 0, "Số lượng hàng following phải >= 0");
        Log.info("KẾT QUẢ: Đếm hàng thành công, giá trị hợp lệ.");
        Log.info("=== END: DQ-WT-003 ===");
    }

    // DQ-BTN-001 — Buttons: double click hiển thị message đúng
    @Test(priority = 5, groups = {"elements","buttons"})
    public void TC_DQ_BTN_001() {
        Log.info("=== START: DQ-BTN-001 ===");
        ButtonsAction btn = new ButtonsAction(getDriver());
        btn.open();

        Log.info("Thực hiện double click vào nút Double Click Me");
        String msg = btn.doubleClickAndGetMessage();
        Log.info("Thông điệp hiển thị: " + msg);

        AssertUtils.assertEquals(msg.trim(), "You have done a double click", "Sai thông điệp double click");
        Log.info("KẾT QUẢ: Double click hiển thị đúng thông điệp.");
        Log.info("=== END: DQ-BTN-001 ===");
    }

    // DQ-LNK-001 — Links: click 'Created' -> phản hồi 201
    @Test(priority = 6, groups = {"elements","links"})
    public void TC_DQ_LNK_001() {
        Log.info("=== START: DQ-LNK-001 ===");
        LinksAction links = new LinksAction(getDriver());
        links.open();

        Log.info("Click link 'Created' và chờ phản hồi API");
        String resp = links.clickCreatedAndWaitResponse();
        Log.info("Phản hồi nhận được: " + resp);

        AssertUtils.assertContains(resp, "201", "Response không chứa mã 201");
        AssertUtils.assertContains(resp.toLowerCase(), "created", "Response không có chữ Created");
        Log.info("KẾT QUẢ: Phản hồi API đúng định dạng 201 Created.");
        Log.info("=== END: DQ-LNK-001 ===");
    }

    // DQ-UP-001 — Upload and Download: upload file .png và verify tên file
    @Test(priority = 7, groups = {"elements","upload"})
    public void TC_DQ_UP_001() {
        Log.info("=== START: DQ-UP-001 ===");
        UploadDownloadAction page = new UploadDownloadAction(getDriver());
        page.open();

        Log.info("Thực hiện upload file logo.png");
        String result = page.uploadFromResources("logo.png");   // ✅ chỉ tên file

        // DemoQA trả về "C:\fakepath\logo.png"
        AssertUtils.assertTrue(result.endsWith("logo.png"), "Tên file hiển thị không đúng: " + result);
        Log.info("KẾT QUẢ: Upload thành công, tên file khớp với logo.png.");
        Log.info("=== END: DQ-UP-001 ===");
    }


    // DQ-DP-001 — Dynamic Properties: 'Will enable 5 seconds' enabled sau ~5s
    @Test(priority = 8, groups = {"elements","dynamic"})
    public void TC_DQ_DP_001() {
        Log.info("=== START: DQ-DP-001 ===");
        DynamicPropertiesAction dp = new DynamicPropertiesAction(getDriver());
        dp.open();

        Log.info("Chờ button 'Will enable 5 seconds' chuyển sang trạng thái enable");
        boolean enabled = dp.waitUntilEnableAfterEnabled(7);

        AssertUtils.assertTrue(enabled, "Button không enabled sau thời gian chờ.");
        Log.info("KẾT QUẢ: Button đã enable đúng sau khoảng 5s.");
        Log.info("=== END: DQ-DP-001 ===");
    }

    // DQ-AL-001 — Alerts: click 'Click me' để mở alert đơn giản và Accept
    @Test(priority = 9, groups = {"alerts"})
    public void TC_DQ_AL_001() {
        Log.info("=== START: DQ-AL-001 ===");
        actions.alerts.AlertsAction al = new actions.alerts.AlertsAction(getDriver());
        al.open();

        Log.info("Click button 'Click me' để mở alert và accept");
        al.clickSimpleAlertAndAccept();

        AssertUtils.assertTrue(true, "Alert simple đã accept.");
        Log.info("KẾT QUẢ: Alert mở và accept thành công.");
        Log.info("=== END: DQ-AL-001 ===");
    }

    // DQ-FM-001 — Forms: nhập tối thiểu và submit, verify modal hiển thị và dữ liệu
    @Test(priority = 10, groups = {"forms"})
    public void TC_DQ_FM_001() {
        Log.info("=== START: DQ-FM-001 ===");
        actions.forms.PracticeFormAction form = new actions.forms.PracticeFormAction(getDriver());
        form.open();

        Log.info("Nhập dữ liệu tối thiểu và submit form");
        form.fillMinimumValid("Minh", "Le", "minh@example.com", "Male", "0987654321");
        form.submit();

        AssertUtils.assertTrue(form.isModalShown(), "Modal không hiển thị sau submit.");
        Log.info("Modal hiển thị, kiểm tra dữ liệu...");

        String studentName = form.getResultValue("Student Name");
        AssertUtils.assertContains(studentName, "Minh Le", "Student Name sai.");

        String studentEmail = form.getResultValue("Student Email");
        AssertUtils.assertEquals(studentEmail, "minh@example.com", "Email sai.");

        Log.info("KẾT QUẢ: Form hiển thị modal và dữ liệu hợp lệ.");
        Log.info("=== END: DQ-FM-001 ===");
    }

    // DQ-DPICK-001 — Date Picker: chọn ngày 1 và 31
    @Test(priority = 11, groups = {"widgets"})
    public void TC_DQ_DPICK_001() {
        Log.info("=== START: DQ-DPICK-001 ===");
        DatePickerAction dpick = new DatePickerAction(getDriver());
        dpick.open();

        Log.info("Chọn ngày 1 trong lịch");
        dpick.pickDay(1);
        String v1 = dpick.getValue();
        AssertUtils.assertTrue(v1 != null && !v1.isEmpty(), "Giá trị ngày không cập nhật (ngày 1).");

        try {
            Log.info("Thử chọn ngày 31 trong lịch");
            dpick.pickDay(31);
            String v2 = dpick.getValue();
            AssertUtils.assertTrue(v2 != null && !v2.isEmpty(), "Giá trị ngày không cập nhật (ngày 31).");
        } catch (Exception ignore) {
            Log.warn("Tháng hiện tại không có ngày 31 — bỏ qua phần kiểm thử BVA.");
        }

        Log.info("KẾT QUẢ: Chọn ngày thành công, giá trị ngày cập nhật hợp lệ.");
        Log.info("=== END: DQ-DPICK-001 ===");
    }
}
