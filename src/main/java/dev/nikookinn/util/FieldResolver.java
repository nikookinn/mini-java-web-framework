package dev.nikookinn.util;

import dev.nikookinn.annotations.MyComponent;

import java.lang.reflect.Method;
/**
 * FieldResolver - Resolves setter methods for field names

 * This component is used to find setter methods for fields during
 * object initialization and dependency injection.
 */
@MyComponent
public class FieldResolver implements SetterResolver{
    /**
     * Finds the setter method for a given field name
     *
     * @param clazz The class to search for setter methods
     * @param fieldName The name of the field
     * @return The setter method, or null if not found
     */
    @Override
    public Method findSetter(Class<?> clazz, String fieldName) {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                return method;
            }
        }
        return null;
    }
}
