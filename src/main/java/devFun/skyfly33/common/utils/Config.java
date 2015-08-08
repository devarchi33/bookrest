package devFun.skyfly33.common.utils;

/**
 * Created by donghoon on 15. 8. 8..
 */

import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Config {

    private static Properties prop;
    private static String configFile = "./skyfly33.properties";

    private Config() {
        prop = new Properties();

        File src = new File(configFile);

        if (!src.exists()) {
            System.out.println("Can't find config file...");
        } else {
            InputStream in = null;
            try {
                in = new FileInputStream(src);
                prop.load(in);
            } catch (FileNotFoundException e) {
                // TODO: handle exception
                System.out.println("Can't find config file...");
            } catch (IOException e) {
                System.out.println("Can't load config file...");
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

    private static class LazyHolder {
        private static final Config INSTANCE = new Config();
    }

    public static Config getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperties(String key) {
        return (String) prop.get(key);
    }

}