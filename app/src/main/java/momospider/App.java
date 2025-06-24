package momospider;

import java.io.IOException;
import java.util.Map;

import momospider.infrastructure.config.ConfigLoader;

public class App {

    public static void main(String[] args) {
        String configPath = "app/src/main/resources/header.properties";
        try {
            Map<String, String> headers = ConfigLoader.loadConfig(configPath);
            System.out.println(headers);
        } catch (IOException e) {
            System.err.println("無法讀取配置文件: " + e.getMessage() + " " + configPath);
        }
    }
}
