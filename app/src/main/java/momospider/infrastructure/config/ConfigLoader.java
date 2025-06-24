package momospider.infrastructure.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {
    /**
     * Load the config from the given path.
     * 
     * @param configPath the path to the config file
     * @return the config
     * @throws IOException if an I/O error occurs
     */
    public static Map<String, String> loadConfig(String configPath) throws IOException {
        Map<String, String> headers = new HashMap<>();
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(configPath)) {
            properties.load(inputStream);
        }

        properties.forEach((key, value) -> headers.put(key.toString(), value.toString()));

        return headers;
    }
}