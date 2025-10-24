package testcase;

import actions.common.AssertUtils;
import actions.common.BaseTest;
import actions.elements.RadioButtonAction;
import common.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlowToElementTestCase extends BaseTest {
    private RadioButtonAction radioButtonAction;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        String browser = System.getProperty("browser", "chrome");              // CHROME|EDGE|FIREFOX|HCHROME
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com/");
        getBrowserDriver(browser, baseUrl);
        radioButtonAction = new RadioButtonAction(getDriver());
    }

    // DQ-RB-002 — Radio Button: "No" bị disable
    @Test(priority = 1, groups = {"elements","radio"},description = "DQ-RB-002 | Elements > Radio Button | 'No' is disabled")
    public void TC_DQ_RB_002() {
        Log.info("=== START: DQ-RB-002 ===");
        radioButtonAction.openRadioButtonPage();

        boolean disabled = radioButtonAction.isNoDisabled(); // nhớ đúng tên method
        AssertUtils.assertTrue(disabled, "'No' phải disabled nhưng phát hiện enable.");
        Log.info("KẾT QUẢ: 'No' bị disabled đúng mong đợi.");
        Log.info("=== END: DQ-RB-002 ===");
    }

}
