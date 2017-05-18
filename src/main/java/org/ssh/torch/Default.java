package org.ssh.torch;

import java.lang.annotation.*;

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
