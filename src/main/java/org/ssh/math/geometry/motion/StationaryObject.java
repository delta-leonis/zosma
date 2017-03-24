package org.ssh.math.geometry.motion;

/**
 * The Class StationaryObject.
 * <p>
 * This class represents an object (in an n-dimensional space) which has a position vector.
 *
 * @param <T> the type parameter
 * @author Rimon Oz
 */
@FunctionalInterface
public interface StationaryObject<T> {

  /**
   * Returns the position vector of the stationary object.
   *
   * @return The position vector of the stationary object.
   */
  T getPosition();
}
