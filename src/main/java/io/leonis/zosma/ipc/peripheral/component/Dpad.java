package io.leonis.zosma.ipc.peripheral.component;

import lombok.Value;

/**
 * The Class Dpad.
 *
 * Describes the 4 buttons in a traditional directional pad.
 *
 * @author Jeroen de Jong
 */
@Value
public class Dpad {

  /**
   * True if pressed, false otherwise.
   */
  private final boolean up, down, left, right;

  /**
   * The Interface Supplier.
   *
   * Supplies the state of a {@link Dpad}.
   *
   * @author Jeroen de Jong
   */
  public interface Supplier {

    /**
     * @return the state of a dpad.
     */
    Dpad getDpad();
  }
}
