package org.ssh.torch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Torch.
 *
 * @author Jeroen de Jong
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Torch {

  /**
   * Name string.
   *
   * @return the string
   */
  String name();

  /**
   * Constructible boolean.
   *
   * @return the boolean
   */
  boolean constructible() default true;
}
