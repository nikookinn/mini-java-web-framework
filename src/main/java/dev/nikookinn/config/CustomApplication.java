package dev.nikookinn.config;

import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.annotations.MyComponentScan;
import dev.nikookinn.dispacher.MiniDispatcher;
/**
 * CustomApplication - Entry point for the application
 * This class provides the static run method to bootstrap the application.
 */
@MyComponent
public class CustomApplication {

    /**
     * Runs the application with the given configuration class
     *
     * @param configClass The configuration class with component scan annotation
     * @throws Exception If application startup fails
     */
    public static void run(Class<?> configClass) throws Exception {
        printBanner();

        if (!configClass.isAnnotationPresent(MyComponentScan.class)) {
            throw new RuntimeException("Missing @MyComponentScan on config class: " + configClass.getName());
        }

        log("Scanning configuration class: " + configClass.getName());
        MyComponentScan scanAnnotation = configClass.getAnnotation(MyComponentScan.class);
        String basePackage = scanAnnotation.value();

        CustomApplicationContext context = new CustomApplicationContext();
        context.scan(basePackage);

        log("All components initialized from package: " + basePackage);

        MiniDispatcher dispatcher = context.getBean(MiniDispatcher.class);
        dispatcher.start(8080);

        log("Application started successfully on port 8080");
    }

    /**
     * Prints the application banner at startup
     */
    private static void printBanner() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("=  CUSTOM SPRING-LIKE FRAMEWORK BOOTING  =");
        System.out.println("==========================================");
        System.out.println("=         :: Powered by Java ::          =");
        System.out.println("==========================================");
        System.out.println();
    }

    private static void log(String message) {
        System.out.println("[CustomFramework] " + message);
    }
}

