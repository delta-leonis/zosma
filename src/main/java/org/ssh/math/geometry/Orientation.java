package org.ssh.math.geometry;

/**
 * The Interface Orientation.
 * <p>
 * This interface describes the functionality of an object which has orientation (with respect to
 * a specific reference angle).
 *
 * @author Rimon Oz
 */
public interface Orientation {

  /**
   * Returns the Y-coordinate (or the second coordinate) of the velocity vector.
   *
   * @return The Y-coordinate (or the second coordinate) of the velocity vector.
   */
  double getOrientationVelocity();

  /**
   * Returns the Y-coordinate (or the second coordinate) of the velocity vector.
   *
   * @return The Y-coordinate (or the second coordinate) of the velocity vector.
   */
  double getOrientation();
}
