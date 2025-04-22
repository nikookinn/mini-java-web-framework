package dev.nikookinn.dispacher;

import dev.nikookinn.annotations.GetMapping;
import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.annotations.PostMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * HandlerMapping - Maps HTTP request paths to handler methods

 * This component scans controllers for request mappings and
 * maintains a registry of path-to-handler mappings.
 */
@MyComponent
public class HandlerMapping {
    private final Map<String, HandlerMethod> getHandlers = new HashMap<>();
    private final Map<String, HandlerMethod> postHandlers = new HashMap<>();

    /**
     * Registers a controller by scanning its methods for mapping annotations
     *
     * @param controller The controller instance to register
     */
    public void registerController(Object controller) {
        Class<?> controllerClass = controller.getClass();

        for (Method method : controllerClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping annotation = method.getAnnotation(GetMapping.class);
                String path = annotation.value();
                getHandlers.put(path, new HandlerMethod(controller, method));
            } else if (method.isAnnotationPresent(PostMapping.class)) {
                PostMapping annotation = method.getAnnotation(PostMapping.class);
                String path = annotation.value();
                postHandlers.put(path, new HandlerMethod(controller, method));
            }
        }
    }
    /**
     * Gets the handler method for a given path and request method
     *
     * @param path The request path
     * @param requestMethod The HTTP method (GET, POST, etc.)
     * @return The handler method or null if not found
     */
    public HandlerMethod getHandler(String path, String requestMethod) {
        if ("GET".equalsIgnoreCase(requestMethod)) {
            return getHandlers.get(path);
        } else if ("POST".equalsIgnoreCase(requestMethod)) {
            return postHandlers.get(path);
        }
        return null;
    }
}