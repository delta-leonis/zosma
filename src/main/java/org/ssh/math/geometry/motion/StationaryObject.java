package org.ssh.math.geometry.motion;

/**
 * The Class StationaryObject.
 *
 * This class represents an object (in an n-dimensional space) which has a position vector.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
public interface StationaryObject<T> {

  /**
   * @return The position vector of the stationary object.
   */
  T getPosition();
}
