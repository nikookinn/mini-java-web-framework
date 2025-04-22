package dev.nikookinn.util;

import dev.nikookinn.annotations.MyAutowired;
import dev.nikookinn.annotations.MyComponent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * MyJsonMapper - Main JSON mapping utility for the framework

 * This component combines parsing, serialization and type conversion
 * to provide complete JSON handling capabilities.
 */
@MyComponent
public class MyJsonMapper {
    @MyAutowired
    private JsonParserStrategy parser;
    @MyAutowired
    private SetterResolver resolver;
    @MyAutowired
    private ValueConverter converter;
    @MyAutowired
    private JsonSerializer serializer;

    /**
     * Parses a JSON string into an object of the specified type
     *
     * @param json The JSON string to parse
     * @param clazz The target class type
     * @return An instance of the target class populated with the JSON data
     * @throws RuntimeException If parsing fails
     */
    public <T> T parse(String json, Class<T> clazz) {
        try {
            Map<String, String> map = parser.parseToMap(json);
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                Method setter = resolver.findSetter(clazz, entry.getKey());
                if (setter == null)
                    throw new IllegalArgumentException("Setter method does not exist: " + entry.getKey());

                Object value = converter.convert(setter.getParameterTypes()[0], entry.getValue());
                setter.invoke(instance, value);
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("JSON parse error: " + e.getMessage(), e);
        }
    }

    /**
     * Converts an object to a JSON string
     *
     * @param obj The object to convert
     * @return A JSON string representation of the object
     * @throws RuntimeException If serialization fails
     */
    public String toJson(Object obj) {
        try {
            return serializer.toJson(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error converting object to JSON:: " + e.getMessage(), e);
        }
    }
}
