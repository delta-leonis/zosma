package org.ssh.math.geometry.motion;

/**
 * The Class MovingObject.
 *
 * This class represents an object (in an n-dimensional space) which has
 * a position and velocity vector.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
public interface MovingObject<T> extends StationaryObject<T> {

  /**
   * @return The velocity vector of the moving object.
   */
  T getVelocity();
}