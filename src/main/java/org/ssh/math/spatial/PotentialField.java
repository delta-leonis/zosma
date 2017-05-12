package org.ssh.math.spatial;

/**
 * The Interface PotentialField.
 *
 * @param <V> The type of the vector which describes the space in which the potential field is
 *            embedded.
 * @author Rimon Oz
 */
public interface PotentialField<V> {

  /**
   * @param positionVector The position vector which points to the point at which the potential is
   *                       measured.
   * @return The potential in the point to which the position vector points.
   */
  double getPotential(final V positionVector);

  /**
   * @param positionVector The position vector which points to the point at which the force is
   *                       measured.
   * @return The force vector due to the potential in the neighborhood of the point to which the
   * position vector points.
   */
  V getForce(final V positionVector);
}
