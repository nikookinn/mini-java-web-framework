package dev.nikookinn.dispacher;

import com.sun.net.httpserver.HttpExchange;
import dev.nikookinn.annotations.MyAutowired;
import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.util.MyJsonMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
/**
 * HandlerAdapter - Processes HTTP requests by invoking appropriate controller methods

 * This class adapts HTTP requests to controller method calls by:
 * 1. Extracting request parameters for POST requests
 * 2. Converting request body to appropriate parameter types
 * 3. Invoking the controller method with provided arguments
 * 4. Serializing the response to JSON and sending it back
 */
@MyComponent
public class HandlerAdapter {
    @MyAutowired
    private MyJsonMapper mapper;

    public HandlerAdapter(MyJsonMapper mapper) {
        this.mapper = mapper;
    }

    public HandlerAdapter() {
    }
    /**
     * Handles an HTTP request by invoking the appropriate controller method
     *
     * @param exchange The HTTP exchange containing request/response information
     * @param handlerMethod The handler method object containing controller and method information
     * @throws Exception If any processing error occurs
     */
    public void handle(HttpExchange exchange, HandlerMethod handlerMethod) throws Exception {
        Object controller = handlerMethod.getController();
        Method method = handlerMethod.getMethod();

        Object[] args = new Object[method.getParameterCount()];

        // For POST requests with one parameter, parse the request body into the parameter type
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod()) && method.getParameterCount() == 1) {
            Class<?> paramType = method.getParameterTypes()[0];
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes());

            Object dto = mapper.parse(body, paramType);
            args[0] = dto;
        }
        // Invoke the controller method with the prepared arguments
        Object result = method.invoke(controller, args);

        // Convert the result to JSON if it's not already a string
        String response;
        if (result instanceof String) {
            response = (String) result;
        } else {
            response = mapper.toJson(result);
        }
        // Send the response
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
