package org.ssh.math.spatial;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface PotentialField.
 *
 * @author Rimon Oz
 */
public interface PotentialField {

  /**
   * @param positionVector The position vector which points to the point at which the potential is
   *                       measured.
   * @return The potential in the point to which the position vector points.
   */
  double getPotential(final INDArray positionVector);

  /**
   * @param positionVector The position vector which points to the point at which the force is
   *                       measured.
   * @return The force vector due to the potential in the neighborhood of the point to which the
   * position vector points.
   */
  INDArray getForce(final INDArray positionVector);

  /**
   * @return The vector pointing to the origin of the potential field.
   */
  INDArray getOrigin();
}
