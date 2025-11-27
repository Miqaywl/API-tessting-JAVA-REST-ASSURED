package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader loader;
    private final Properties properties = new Properties();

    private ConfigLoader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find application.properties in resources folder");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static ConfigLoader getInstance() {
        if (loader == null) {
            loader = new ConfigLoader();
        }
        return loader;
    }

    private String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' is missing in application.properties");
        }
        return value.trim();
    }

    public String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public String getEnvironment() {
        return getProperty("environment");
    }
}