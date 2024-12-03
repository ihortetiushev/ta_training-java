package final_task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Properties properties = new Properties();
    static {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("test_prop.properties")) {
            if (input == null) {
                throw new
                        FileNotFoundException("Resource file 'test_prop.properties' not found in classpath!");
            }
            properties.load(input);
            System.out.println("Properties loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperties(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
