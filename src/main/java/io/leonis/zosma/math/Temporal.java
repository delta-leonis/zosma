package io.leonis.zosma.math;

/**
 * The Interface Temporal.
 *
 * This interface describes the functionality of an object which can be timestamped.
 *
 * @author Rimon Oz
 */
public interface Temporal {

  /**
   * @return The last seen time of the timestampable object.
   */
  long getTimestamp();
}
