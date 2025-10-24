// DateTimeUtils.java
package utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

public final class DateTimeUtils {
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    private static final DateTimeFormatter DEFAULT_DT   = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DEFAULT_DATE = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final DateTimeFormatter DEFAULT_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    private DateTimeUtils() {}

    public static String getCurrentDateTime() {
        return LocalDateTime.now(DEFAULT_ZONE).format(DEFAULT_DT);
    }

    public static String getCurrentDateTime(String pattern) {
        String strictPattern = pattern.replace("yyyy", "uuuu");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(strictPattern);
        return LocalDateTime.now(DEFAULT_ZONE).format(fmt);
    }

    public static String getCurrentDate() {
        return LocalDate.now(DEFAULT_ZONE).format(DEFAULT_DATE);
    }

    public static String getCurrentTime() {
        return LocalTime.now(DEFAULT_ZONE).format(DEFAULT_TIME);
    }

    public static LocalDateTime parseDateTime(String value, String pattern) {
        try {
            String strictPattern = Objects.requireNonNull(pattern).replace("yyyy", "uuuu");
            DateTimeFormatter fmt = DateTimeFormatter
                    .ofPattern(strictPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(Objects.requireNonNull(value), fmt);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Datetime không đúng định dạng: " + value + " | pattern=" + pattern, ex);
        }
    }

    public static String addDays(String baseDateTime, String pattern, long days) {
        String strictPattern = pattern.replace("yyyy", "uuuu");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(strictPattern).withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime ldt = parseDateTime(baseDateTime, pattern);
        return ldt.plusDays(days).format(fmt);
    }

    public static String addHours(String baseDateTime, String pattern, long hours) {
        String strictPattern = pattern.replace("yyyy", "uuuu");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(strictPattern).withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime ldt = parseDateTime(baseDateTime, pattern);
        return ldt.plusHours(hours).format(fmt);
    }

    public static long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    public static String fromTimestamp(long epochMillis, String pattern) {
        // format không cần STRICT, nhưng vẫn đồng bộ uuuu để round-trip đẹp
        String strictPattern = pattern.replace("yyyy", "uuuu");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(strictPattern).withZone(DEFAULT_ZONE);
        return fmt.format(Instant.ofEpochMilli(epochMillis));
    }

    public static boolean isValidDateTime(String value, String pattern) {
        try {
            parseDateTime(value, pattern); // đã STRICT + uuuu bên trong
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
