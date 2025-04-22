package dev.nikookinn.dispacher;


import java.lang.reflect.Method;
/**
 * HandlerMethod - Holds information about a controller method mapping

 * This class encapsulates a controller instance and its method
 * that should be invoked for a specific HTTP request.
 */
public class HandlerMethod {
    private final Object controller;
    private final Method method;

    /**
     * Creates a new HandlerMethod with the specified controller and method
     *
     * @param controller The controller instance
     * @param method The method to be invoked
     */
    public HandlerMethod(Object controller, Method method){
        this.controller=controller;
        this.method=method;
    }

    /**
     * Gets the controller instance
     *
     * @return The controller instance
     */
    public Object getController() {
        return controller;
    }

    /**
     * Gets the method to be invoked
     *
     * @return The method
     */
    public Method getMethod() {
        return method;
    }
}
