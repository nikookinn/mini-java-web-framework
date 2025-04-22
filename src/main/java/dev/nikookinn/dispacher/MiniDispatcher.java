package dev.nikookinn.dispacher;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dev.nikookinn.annotations.MyAutowired;
import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.annotations.MyController;
import dev.nikookinn.config.CustomApplicationContext;
import dev.nikookinn.util.MyJsonMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
/**
 * MiniDispatcher - Central dispatcher for HTTP requests in the framework

 * This component initializes the application, registers controllers,
 * and dispatches incoming HTTP requests to the appropriate handlers.
 */
@MyComponent
public class MiniDispatcher {

    @MyAutowired
    private CustomApplicationContext context;
    @MyAutowired
    private HandlerMapping handlerMapping;
    @MyAutowired
    private HandlerAdapter handlerAdapter;
    @MyAutowired
    private MyJsonMapper jsonMapper;

    /**
     * Starts the HTTP server and initializes all controllers
     *
     * @param port The port to listen on
     * @throws IOException If server cannot be started
     */
    public void start(int port) throws IOException {
        for (Map.Entry<Class<?>, Object> entry : context.getBeanMap().entrySet()) {
            Class<?> clazz = entry.getKey();
            Object instance = entry.getValue();

            if (clazz.isAnnotationPresent(MyController.class)) {
                handlerMapping.registerController(instance);
            }
        }
        // Create and start the HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this::handle);
        server.setExecutor(null);
        server.start();
    }

    /**
     * Handles incoming HTTP requests by finding the appropriate controller method
     * and delegating to the HandlerAdapter
     *
     * @param exchange The HTTP exchange containing request/response information
     * @throws IOException If an I/O error occurs
     */
    private void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String requestMethod = exchange.getRequestMethod();

        // Find the appropriate handler method for this path and method
        HandlerMethod handler = handlerMapping.getHandler(path, requestMethod);
        if (handler == null) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }

        try {
            // Delegate to the handler adapter to process the request
            handlerAdapter.handle(exchange, handler);
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }

}
