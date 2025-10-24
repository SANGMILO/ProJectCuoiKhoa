package testcase.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.StringUtils;
import static org.testng.Assert.*;
import static actions.common.AssertUtils.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class StringUtilsTest {


    @Test
    public void testCapitalizeFirst() {
        assertEquals(StringUtils.capitalizeFirst("sang"), "Sang");
        assertNull(StringUtils.capitalizeFirst(null));
        assertEquals(StringUtils.capitalizeFirst(""), "");
    }


    @Test
    public void testCaseConversions() {
        assertEquals(StringUtils.toLower("ABC"), "abc");
        assertEquals(StringUtils.toUpper("abc"), "ABC");
        assertNull(StringUtils.toLower(null));
        assertNull(StringUtils.toUpper(null));
    }


    @Test
    public void testRemoveSpecialChars() {
        assertEquals(StringUtils.removeSpecialChars("Hello@#% World!!!"), "Hello World");
    }


    @Test
    public void testRemoveVietnameseAccents() {
        assertEquals(StringUtils.removeVietnameseAccents("Tiếng Việt dễ thương đấy"), "Tieng Viet de thuong day");
    }


    @Test
    public void testNormalizeKeyword() {
        assertEquals(StringUtils.normalizeKeyword(" Ánh sáng "), "anh sang");
    }


    @Test
    public void testTrimAllSpaces() {
        assertEquals(StringUtils.trimAllSpaces(" xin chao cac ban "), "xin chao cac ban");
    }


    @Test
    public void testTruncate() {
        assertEquals(StringUtils.truncate("abcdef", 4), "abc…");
        assertEquals(StringUtils.truncate("abc", 10), "abc");
    }


    @DataProvider
    public Object[][] numberCases() {
        return new Object[][]{
                {"abc", false},
                {"abc1", true},
                {"1abc", true},
                {"", false},
                {null, false}
        };
    }


    @Test(dataProvider = "numberCases")
    public void testContainsNumber(String input, boolean expected) {
        assertEquals(StringUtils.containsNumber(input), expected);
    }
}
