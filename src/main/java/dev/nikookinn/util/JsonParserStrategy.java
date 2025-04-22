package dev.nikookinn.util;

import java.util.Map;
/**
 * JsonParserStrategy - Interface for JSON parsing strategies

 * This interface defines the contract for JSON parsing implementations
 * to convert JSON strings into a map of field names to values.
 */
public interface JsonParserStrategy {
    /**
     * Parses a JSON string into a map of key-value pairs
     *
     * @param json The JSON string to parse
     * @return A map of field names to their string values
     */
    Map<String, String> parseToMap(String json);
}
