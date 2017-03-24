package org.ssh.torch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Default.
 *
 * @author Jeroen de Jong
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {

  /**
   * Value string.
   *
   * @return the string
   */
  String value();
}
