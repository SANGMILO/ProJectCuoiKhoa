// src/test/java/report/ExtentTestManager.java
package reports;

import java.util.concurrent.ConcurrentHashMap;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ConcurrentHashMap<Long, ExtentTest> map = new ConcurrentHashMap<>();

    public static synchronized ExtentTest getTest() {
        return map.get(Thread.currentThread().getId());
    }

    public static synchronized void setTest(ExtentTest test) {
        map.put(Thread.currentThread().getId(), test);
    }

    public static synchronized void remove() {
        map.remove(Thread.currentThread().getId());
    }
}