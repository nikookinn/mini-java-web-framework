package dev.nikookinn.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * MyController - Annotation for web controllers

 * Classes marked with this annotation will be detected during component scanning
 * and registered as web controllers in the application. Similar to Spring's
 * @Controller annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyController {

}
