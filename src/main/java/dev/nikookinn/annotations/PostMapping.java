package dev.nikookinn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * PostMapping - Annotation for HTTP POST request mappings

 * Methods marked with this annotation will be registered to handle HTTP POST
 * requests for the specified path. Similar to Spring's @PostMapping annotation.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostMapping {
    /**
     * The URL path to map this method to
     *
     * @return The URL path
     */
    String value();
}
