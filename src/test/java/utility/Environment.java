package utility;

import java.io.*;
import java.util.Optional;
import java.util.Properties;

public class Environment {

    public static String getValueEnvironment(String key){
        Properties properties = new Properties();
        String selectEnvironment = Optional.ofNullable(System.getenv("ENVIRONMENT")).orElse("dev");

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(getPathResources() + selectEnvironment + ".properties"));
            InputStream inputStream = Environment.class.getResourceAsStream(String.join(selectEnvironment, "/",".properties"));
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String a = properties.getProperty(key);
        return properties.getProperty(key);
    }

    public  static String getPathResources(){
        StringBuffer path = new StringBuffer();
        path.append(File.separator);
        path.append("src");
        path.append(File.separator);
        path.append("test");
        path.append(File.separator);
        path.append("resources");
        path.append(File.separator);
        return (new File("")).getAbsolutePath() + path;
    }
}
