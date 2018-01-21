package io.leonis.zosma.ipc.peripheral.component.xbox;

/**
 * The Class RightClusterSupplier.
 *
 * Supplies the state of any right xbox button.
 *
 * @author Jeroen de Jong
 */
public interface RightClusterSupplier {

  /**
   * @return True if A is pressed, false otherwise.
   */
  boolean isAPressed();

  /**
   * @return True if B is pressed, false otherwise.
   */
  boolean isBPressed();

  /**
   * @return True if X is pressed, false otherwise.
   */
  boolean isXPressed();

  /**
   * @return True if Y is pressed, false otherwise.
   */
  boolean isYPressed();
}
