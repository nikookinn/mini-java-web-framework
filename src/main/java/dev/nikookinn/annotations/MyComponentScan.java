package dev.nikookinn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyComponentScan - Annotation for defining component scan packages

 * This annotation is applied to configuration classes to specify which
 * base package should be scanned for components. Similar to Spring's
 * @ComponentScan annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponentScan {

    /**
     * Base package to scan for components
     *
     * @return The base package path
     */
    String value();
}
