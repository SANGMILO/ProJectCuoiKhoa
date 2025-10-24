package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log helper class for unified logging across framework.
 * Works with log4j2.xml under resources.
 */
public final class Log {
    private static final Logger log = LogManager.getLogger(Log.class);

    private Log() {} // ngăn tạo instance

    // Info Level Logs
    public static void info(String message) {
        log.info(message);
    }

    // Warn Level Logs
    public static void warn(String message) {
        log.warn(message);
    }

    // Error Level Logs
    public static void error(String message) {
        log.error(message);
    }

    // Fatal Level Logs
    public static void fatal(String message) {
        log.fatal(message);
    }

    // Debug Level Logs
    public static void debug(String message) {
        log.debug(message);
    }
}
