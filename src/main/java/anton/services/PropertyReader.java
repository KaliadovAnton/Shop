package anton.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final String DB_PROPS_PATH = "C:\\Users\\kaaba\\IdeaProjects\\servletapp\\src\\main\\resources\\Properties.txt";
    private static final Properties properties = new Properties();


    public static String getProperty(String property) throws IOException {
        FileInputStream inputStream = new FileInputStream(DB_PROPS_PATH);
        properties.load(inputStream);
        inputStream.close();
        System.out.println(properties.getProperty(property)+" " +property);
        return properties.getProperty(property);
    }
}
