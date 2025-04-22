package dev.nikookinn.util;

import dev.nikookinn.annotations.MyComponent;

import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * JsonSerializer - Converts Java objects to JSON strings

 * This component provides functionality to serialize Java objects
 * into JSON format for HTTP responses.
 */
@MyComponent
public class JsonSerializer {

    /**
     * Converts an object to a JSON string
     *
     * @param obj The object to convert
     * @return A JSON string representation of the object
     * @throws IllegalAccessException If field values cannot be accessed
     */
    public String toJson(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringJoiner joiner = new StringJoiner(",", "{", "}");

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            String jsonValue = value != null ? value.toString() : "null";
            joiner.add("\"" + field.getName() + "\":\"" + jsonValue + "\"");
        }

        return joiner.toString();
    }
}
