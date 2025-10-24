package testcase.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DateTimeUtils;
import java.time.LocalDateTime;

import static actions.common.AssertUtils.*;

public class DateTimeUtilsTest {


    @Test
    public void testGetCurrentDateTime_DefaultPattern_NotEmpty() {
        String now = DateTimeUtils.getCurrentDateTime();
        assertTrue(now.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }


    @Test
    public void testGetCurrentDate_NotEmpty() {
        String d = DateTimeUtils.getCurrentDate();
        assertTrue(d.matches("\\d{4}-\\d{2}-\\d{2}"));
    }


    @Test
    public void testGetCurrentTime_NotEmpty() {
        String t = DateTimeUtils.getCurrentTime();
        assertTrue(t.matches("\\d{2}:\\d{2}:\\d{2}"));
    }


    @Test
    public void testParse_Then_AddDays_AddHours_Roundtrip() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String base = "2025-01-31 23:30:00";
        LocalDateTime ldt = DateTimeUtils.parseDateTime(base, pattern);
        assertEquals(ldt.getMonthValue(), 1);
        String plusDays = DateTimeUtils.addDays(base, pattern, 1); // sang 2025-02-01
        assertTrue(plusDays.startsWith("2025-02-01"));
        String plusHours = DateTimeUtils.addHours(base, pattern, 2); // 2025-02-01 01:30:00
        assertTrue(plusHours.startsWith("2025-02-01 01:30:00"));
    }


    @Test
    public void testTimestamp_ConvertBothWays() {
        long ts = DateTimeUtils.getCurrentTimestamp();
        String s = DateTimeUtils.fromTimestamp(ts, "yyyy-MM-dd HH:mm:ss");
        assertTrue(DateTimeUtils.isValidDateTime(s, "yyyy-MM-dd HH:mm:ss"));
    }


    @DataProvider
    public Object[][] invalidDates() {
        return new Object[][] {
                {"2025-13-10 10:00:00", "yyyy-MM-dd HH:mm:ss"},
                {"2025-02-29 00:00:00", "yyyy-MM-dd HH:mm:ss"}, // 2025 không nhuận
                {"not-a-date", "yyyy-MM-dd"}
        };
    }


    @Test(dataProvider = "invalidDates")
    public void testIsValidDateTime_Invalid(String value, String pattern) {
        assertFalse(DateTimeUtils.isValidDateTime(value, pattern));
    }
}
