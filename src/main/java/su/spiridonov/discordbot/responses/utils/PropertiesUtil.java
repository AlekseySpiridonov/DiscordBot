package su.spiridonov.discordbot.responses.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties readPropertiesFile(String fileName) throws Exception {
        FileInputStream fileInputStream = null;
        Properties properties = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        return properties;
    }
}

