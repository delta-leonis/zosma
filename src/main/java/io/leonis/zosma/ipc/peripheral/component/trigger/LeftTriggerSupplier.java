package io.leonis.zosma.ipc.peripheral.component.trigger;

/**
 * The Class LeftTriggerSupplier.
 *
 * Supplies the state of the left trigger.
 *
 * @author Jeroen de Jong
 */
public interface LeftTriggerSupplier {

  /**
   * The amount the left trigger is pressed.
   *
   * @return A float between 0-1.
   */
  float getLeftTrigger();
}
