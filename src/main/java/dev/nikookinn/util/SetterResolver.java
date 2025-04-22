package dev.nikookinn.util;

import java.lang.reflect.Method;
/**
 * SetterResolver - Interface for resolving setter methods

 * This interface defines the contract for implementations that
 * find setter methods for fields in target classes.
 */
public interface SetterResolver{
    /**
     * Finds the setter method for a given field name
     *
     * @param clazz The class to search for setter methods
     * @param fieldName The name of the field
     * @return The setter method, or null if not found
     */
    Method findSetter(Class<?> clazz, String fieldName);
}
