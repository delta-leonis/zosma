package io.leonis.zosma.ipc.peripheral.component.xbox;

/**
 * The Class BackSupplier.
 *
 * Supplies the state of the back button on an xbox controller.
 *
 * @author Jeroen de Jong
 */
public interface BackSupplier {

  /**
   * @return True if the back button is pressed, false otherwise.
   */
  boolean isBackPressed();
}
