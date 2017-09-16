package org.ssh.math.geometry.motion;

/**
 * The Class MovingObject.
 *
 * This class represents an object (in a 3-dimensional space) which has
 * a position and velocity vector.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
public interface MovingObject3D<T> extends MovingObject2D<T>, StationaryObject3D<T> {

  /**
   * @return The Z-coordinate (or the third coordinate) of the velocity vector.
   */
  double getZVelocity();
}
