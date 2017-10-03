package io.leonis.math.geometry;

/**
 * The Interface Orientation.
 *
 * This interface describes the functionality of an object which has orientation (with respect to a
 * specific reference angle).
 *
 * @author Rimon Oz
 */
public interface Orientation {

  /**
   * @return The rate at which the orientation of the object is changing.
   */
  double getOrientationVelocity();

  /**
   * @return The orientation of the object.
   */
  double getOrientation();
}
