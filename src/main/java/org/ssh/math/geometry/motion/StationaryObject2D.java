package org.ssh.math.geometry.motion;

/**
 * The Class StationaryObject2D.
 *
 * This class represents an object (in a 2-dimensional space) which has a position vector.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
public interface StationaryObject2D<T> extends StationaryObject<T> {

  /**
   * @return The X-coordinate (or the first coordinate) of the position vector.
   */
  double getX();

  /**
   * @return The Y-coordinate (or the second coordinate) of the position vector.
   */
  double getY();

}
