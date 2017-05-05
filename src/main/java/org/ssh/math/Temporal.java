package org.ssh.math;

/**
 * The Interface Temporal.
 * <p>
 * This interface describes the functionality of an object which can be timestamped.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Temporal {

  /**
   * Returns the last seen time of the timestampable object.
   *
   * @return The last seen time of the timestampable object.
   */
  long getTimestamp();
}
