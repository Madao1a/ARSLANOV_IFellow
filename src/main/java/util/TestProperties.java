package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private static Properties PROPERTIES;
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/application.properties")){
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getPoperty(String key){
        return PROPERTIES.getProperty(key);
    }
}
