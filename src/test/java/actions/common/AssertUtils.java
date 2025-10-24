package actions.common;

import org.testng.Assert;

/**
 * AssertUtils - Lớp tiện ích giúp kiểm tra điều kiện trong test.
 * Gói lại các phương thức Assert của TestNG, hỗ trợ thêm message tùy chọn.
 */
public class AssertUtils {

    // 1. Assert Equals
    public static void assertEquals(String actual, String expected, String... message) {
        if (message.length > 0)
            Assert.assertEquals(actual, expected, message[0]);
        else
            Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(boolean actual, boolean expected, String... message) {
        if (message.length > 0)
            Assert.assertEquals(actual, expected, message[0]);
        else
            Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(int actual, int expected, String... message) {
        if (message.length > 0)
            Assert.assertEquals(actual, expected, message[0]);
        else
            Assert.assertEquals(actual, expected);
    }

    // 2. Assert Not Equals
    public static void assertNotEquals(String actual, String expected, String... message) {
        if (message.length > 0)
            Assert.assertNotEquals(actual, expected, message[0]);
        else
            Assert.assertNotEquals(actual, expected);
    }

    public static void assertNotEquals(int actual, int expected, String... message) {
        if (message.length > 0)
            Assert.assertNotEquals(actual, expected, message[0]);
        else
            Assert.assertNotEquals(actual, expected);
    }

    public static void assertNotEquals(boolean actual, boolean expected, String... message) {
        if (message.length > 0)
            Assert.assertNotEquals(actual, expected, message[0]);
        else
            Assert.assertNotEquals(actual, expected);
    }

    // 3. Assert Equals Ignore Case
    public static void assertEqualsIgnoreCase(String actual, String expected, String... message) {
        if (actual == null || expected == null) {
            Assert.fail("Actual hoặc Expected null trong assertEqualsIgnoreCase()");
        }
        boolean result = actual.equalsIgnoreCase(expected);
        if (message.length > 0)
            Assert.assertTrue(result, message[0]);
        else
            Assert.assertTrue(result, "Expected: [" + expected + "], but found: [" + actual + "]");
    }

    // 4. Assert True
    public static void assertTrue(boolean condition, String... message) {
        if (message.length > 0)
            Assert.assertTrue(condition, message[0]);
        else
            Assert.assertTrue(condition);
    }

    // 5. Assert False
    public static void assertFalse(boolean condition, String... message) {
        if (message.length > 0)
            Assert.assertFalse(condition, message[0]);
        else
            Assert.assertFalse(condition);
    }

    // 6. Assert Contains
    public static void assertContains(String fullText, String subText, String... message) {
        if (fullText == null) {
            Assert.fail("Chuỗi fullText bị null trong assertContains()");
        }
        boolean result = fullText.contains(subText);
        if (message.length > 0)
            Assert.assertTrue(result, message[0]);
        else
            Assert.assertTrue(result, "Chuỗi [" + fullText + "] không chứa [" + subText + "]");
    }
}
