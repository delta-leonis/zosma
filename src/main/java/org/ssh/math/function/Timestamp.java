package org.ssh.math.function;

/**
 * The Interface Timestamp.
 * <p>
 * This interface describes the functionality of an object which can be timestamped.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Timestamp {

  /**
   * Returns the last seen time of the timestampable object.
   *
   * @return The last seen time of the timestampable object.
   */
  double getTimestamp();
}
