package dev.nikookinn.util;

import dev.nikookinn.annotations.MyComponent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JsonParser - Parses JSON strings into maps of key-value pairs

 * This component implements the JsonParserStrategy interface to
 * provide JSON parsing functionality for the framework.
 */
@MyComponent
public class JsonParser implements JsonParserStrategy{

    /**
     * Parses a JSON string into a map of key-value pairs
     *
     * @param json The JSON string to parse
     * @return A map of field names to their string values
     * @throws IllegalArgumentException If the JSON format is invalid
     */
    @Override
    public Map<String, String> parseToMap(String json) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("Json data is empty");
        }

        json = json.trim();
        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new IllegalArgumentException("Invalid Json format");
        }

        json = json.substring(1, json.length() - 1).trim();

        // Use regex to extract key-value pairs
        Pattern pattern = Pattern.compile("\"(.*?)\"\\s*:\\s*(\".*?\"|\\d+|true|false|null)");
        Matcher matcher = pattern.matcher(json);

        Map<String, String> map = new LinkedHashMap<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            String rawValue = matcher.group(2).replaceAll("^\"|\"$", "");
            map.put(key, rawValue);
        }

        return map;
    }
}
