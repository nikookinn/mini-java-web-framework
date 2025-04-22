package dev.nikookinn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * GetMapping - Annotation for HTTP GET request mappings

 * Methods marked with this annotation will be registered to handle HTTP GET
 * requests for the specified path. Similar to Spring's @GetMapping annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetMapping {

    /**
     * The URL path to map this method to
     *
     * @return The URL path
     */
    String value();
}
