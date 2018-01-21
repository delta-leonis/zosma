package io.leonis.zosma.ipc.peripheral.component.trigger;

/**
 * The Class RightTriggerSupplier.
 *
 * Supplies the state of the right trigger.
 *
 * @author Jeroen de Jong
 */
public interface RightTriggerSupplier {

  /**
   * The amount the right trigger is pressed.
   *
   * @return A float between 0-1.
   */
  float getRightTrigger();
}