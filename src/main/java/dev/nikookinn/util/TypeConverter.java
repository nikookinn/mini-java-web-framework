package dev.nikookinn.util;

import dev.nikookinn.annotations.MyComponent;
/**
 * TypeConverter - Converts string values to typed Java objects

 * This component implements the ValueConverter interface to convert
 * string values from JSON to appropriate Java types.
 */
@MyComponent
public class TypeConverter implements ValueConverter{

    /**
     * Converts a string value to the specified type
     *
     * @param type The target type class
     * @param value The string value to convert
     * @return The converted value
     * @throws IllegalArgumentException If conversion fails or type is unsupported
     */
    @Override
    public Object convert(Class<?> type, String value) {
        try {
            if (type == String.class) return value;
            if (type == int.class || type == Integer.class) return Integer.parseInt(value);
            if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
            if (type == long.class || type == Long.class) return Long.parseLong(value);
            if (type == double.class || type == Double.class) return Double.parseDouble(value);
            if (type == float.class || type == Float.class) return Float.parseFloat(value);

            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        } catch (Exception e) {
            throw new IllegalArgumentException("Data conversion error: '" + value + "' type " + type.getSimpleName());
        }
    }
}
