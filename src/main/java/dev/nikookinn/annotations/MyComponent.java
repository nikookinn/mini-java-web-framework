package dev.nikookinn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyComponent - Annotation for component scanning

 * Classes marked with this annotation will be automatically detected during
 * component scanning and registered as beans in the application context.
 * Similar to Spring's @Component annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponent {
}
