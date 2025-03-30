package utils;

import java.util.ResourceBundle;

public class TestDataReader {

    private static final ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle(System.getProperty("env", "qa"));
    }

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
