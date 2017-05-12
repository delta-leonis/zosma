package org.ssh.math.geometry.motion;

/**
 * The Class MovingObject2D.
 * <p>
 * This class represents an object (in a 2-dimensional space) which
 * has a position and velocity vector.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
public interface MovingObject2D<T> extends MovingObject<T>, StationaryObject2D<T> {

  /**
   * @return The X-coordinate (or the first coordinate) of the velocity vector.
   */
  double getXVelocity();

  /**
   * @return The Y-coordinate (or the second coordinate) of the velocity vector.
   */
  double getYVelocity();
}
