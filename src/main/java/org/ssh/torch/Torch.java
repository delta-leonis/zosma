package org.ssh.torch;

import java.lang.annotation.*;

/**
 * The Interface Torch.
 *
 * @author Jeroen de Jong
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Torch {

  /**
   * @return The name of the torch.
   */
  String name();

  /**
   * @return True if torch is constructible, false otherwise.
   */
  boolean constructible() default true;
}
