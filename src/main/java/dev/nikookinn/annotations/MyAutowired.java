package dev.nikookinn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * MyAutowired - Annotation for dependency injection

 * Fields marked with this annotation will be automatically injected with
 * an instance of the appropriate type from the application context.
 * Similar to Spring's @Autowired annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAutowired {
}
