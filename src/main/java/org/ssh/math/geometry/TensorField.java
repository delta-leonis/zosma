package org.ssh.math.geometry;

/**
 * The Interface TensorField.
 *
 * @param <V> The type of the tensor which describes the space in which the potential field is
 *            embedded.
 * @author Rimon Oz
 */
public interface TensorField<V> {

  /**
   * Returns the tensor in the point to which the position vector points.
   *
   * @param positionVector The position vector which points to the point at which the potential is
   *                       measured.
   * @return The potential in the point to which the position vector points.
   */
  V getTensor(V positionVector);

  /**
   * Returns the covariant derivative of the tensor field in the point to which the position
   * vector points.
   *
   * @param positionVector The position vector which points to the point at which the potential is
   *                       measured.
   * @return The potential in the point to which the position vector points.
   */
  V getCovariantDerivative(V positionVector);
}
