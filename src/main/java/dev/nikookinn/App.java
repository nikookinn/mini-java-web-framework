package dev.nikookinn;

import dev.nikookinn.config.AppConfig;
import dev.nikookinn.config.CustomApplication;
/**
 * App - Main application entry point

 * This class contains the main method that bootstraps the custom Spring-like framework
 * by calling CustomApplication.run() with the main configuration class.
 */
public class App {
    /**
     * Application entry point
     *
     * @param args Command line arguments (not used)
     * @throws Exception If application startup fails
     */
    public static void main(String[] args) throws Exception {
        CustomApplication.run(AppConfig.class);
    }
}
