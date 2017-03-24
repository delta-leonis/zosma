package org.ssh.math.geometry.motion;

/**
 * The Class StationaryObject2D.
 * <p>
 * This class represents an object (in a 2-dimensional space) which has a position vector.
 *
 * @param <T> the type parameter
 * @author Rimon Oz
 */
public interface StationaryObject2D<T> extends StationaryObject<T> {

  /**
   * Returns the X-coordinate (or the first coordinate) of the position vector.
   *
   * @return The X-coordinate (or the first coordinate) of the position vector.
   */
  double getX();

  /**
   * Returns the Y-coordinate (or the second coordinate) of the position vector.
   *
   * @return The Y-coordinate (or the second coordinate) of the position vector.
   */
  double getY();

}
