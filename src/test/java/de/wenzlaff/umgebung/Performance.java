package de.wenzlaff.umgebung;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;

/**
 * Annotation für Performace Tests. 
 * 
 * Zentral ausgeschaltet.
 * 
 * @author Thomas Wenzlaff
 */
@Disabled
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("Performance")
public @interface Performance {
}