package io.leonis.zosma.ipc.peripheral.component.bumper;

/**
 * The Class RightBumperSupplier.
 *
 * Supplies the state of the right bumper.
 *
 * @author Jeroen de Jong
 */
public interface RightBumperSupplier {

  /**
   * @return True if the right bumper is pressed, false otherwise.
   */
  boolean isRightBumperPressed();
}
