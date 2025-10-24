package utils;
import java.text.Normalizer;
import java.util.Objects;
import java.util.regex.Pattern;


public final class StringUtils {
    private static final Pattern SPECIAL_CHARS = Pattern.compile("[^\\p{L}0-9 ]+");
    private static final Pattern MULTI_SPACES = Pattern.compile("\\s+");
    private static final Pattern NUMBER_PATTERN = Pattern.compile(".*[0-9].*");


    private StringUtils() {}


    /** Kiểm tra chuỗi rỗng hoặc null */
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }


    /** Viết hoa chữ cái đầu tiên (giữ nguyên phần còn lại). */
    public static String capitalizeFirst(String s) {
        if (isNullOrEmpty(s)) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }


    /** Chuyển thành chữ thường toàn bộ. */
    public static String toLower(String s) { return s == null ? null : s.toLowerCase(); }


    /** Chuyển thành chữ hoa toàn bộ. */
    public static String toUpper(String s) { return s == null ? null : s.toUpperCase(); }


    /** Xóa ký tự đặc biệt, chỉ giữ chữ cái (mọi ngôn ngữ), số và khoảng trắng đơn. */
    public static String removeSpecialChars(String s) {
        if (s == null) return null;
        String cleaned = SPECIAL_CHARS.matcher(s).replaceAll("");
        return trimAllSpaces(cleaned);
    }


    /** Loại bỏ dấu tiếng Việt (và dấu của nhiều ngôn ngữ Latinh). */
    public static String removeVietnameseAccents(String s) {
        if (s == null) return null;
        String norm = Normalizer.normalize(s, Normalizer.Form.NFD);
        String noMarks = norm.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    // Chuẩn hóa riêng ký tự đ/Đ
        noMarks = noMarks.replace('đ', 'd').replace('Đ', 'D');
        return noMarks;
    }


    /** Viết thường + bỏ dấu (so sánh keyword). */
    public static String normalizeKeyword(String s) {
        if (s == null) return null;
        return toLower(removeVietnameseAccents(trimAllSpaces(s)));
    }


    /** Rút gọn khoảng trắng dư thừa (trim 2 đầu + gộp spaces). */
    public static String trimAllSpaces(String s) {
        if (s == null) return null;
        return MULTI_SPACES.matcher(s.trim()).replaceAll(" ");
    }


    /** Cắt chuỗi vượt quá độ dài, thêm "…" nếu bị cắt. */
    public static String truncate(String s, int maxLen) {
        Objects.requireNonNull(s);
        if (maxLen < 0) throw new IllegalArgumentException("maxLen must be >= 0");
        if (s.length() <= maxLen) return s;
        if (maxLen == 0) return "";
        return s.substring(0, Math.max(0, maxLen - 1)) + "…";
    }


    /** Kiểm tra chuỗi có chứa ít nhất một chữ số không. */
    public static boolean containsNumber(String s) {
        if (s == null) return false;
        return NUMBER_PATTERN.matcher(s).matches();
    }
}