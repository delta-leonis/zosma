package io.leonis.zosma.ipc.peripheral.component.bumper;

/**
 * The Class LeftBumperSupplier.
 *
 * Supplies the state of the left bumper.
 *
 * @author Jeroen de Jong
 */
public interface LeftBumperSupplier {

  /**
   * @return True if the left bumper is pressed, false otherwise.
   */
  boolean isLeftBumperPressed();
}
