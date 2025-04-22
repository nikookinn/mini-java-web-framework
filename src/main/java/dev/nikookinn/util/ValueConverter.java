package dev.nikookinn.util;
/**
 * ValueConverter - Interface for type conversion

 * This interface defines the contract for implementations that
 * convert string values to appropriate Java types.
 */
public interface ValueConverter {
    /**
     * Converts a string value to the specified type
     *
     * @param type The target type class
     * @param value The string value to convert
     * @return The converted value
     */
    Object convert(Class<?> type, String value);
}
