package tech.verenti.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfiguration implements Config {

    private static Properties MY_CONFIG = null;

     static void populate(String envName) {
        String propsResourceName
                = "/" + envName + "_config.properties";
        try (InputStream input = EnvironmentConfiguration.class.getResourceAsStream(propsResourceName)) {
            MY_CONFIG = new Properties();
            MY_CONFIG.load(input);

        } catch (IOException e) {
            System.out.println("Cannot load environment properties");
        }
    }


    protected static String getBaseURL() {
        return MY_CONFIG.getProperty("baseURL");
    }

}
