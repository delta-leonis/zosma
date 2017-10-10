package io.leonis.zosma.math.spatial;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface PotentialField.
 *
 * @author Rimon Oz
 */
public interface PotentialField {

  /**
   * @param positionVector The vector pointing to the position at which the potential is
   *                       calculated.
   * @return The potential in the point to which the position vector points.
   */
  INDArray getPotential(final INDArray positionVector);

  /**
   * @param positionVector The vector pointing to the position at which the force vector is
   *                       calculated.
   * @return The force vector due to the potential in the neighborhood of the point to which the
   * position vector points.
   */
  INDArray getForce(final INDArray positionVector);

  /**
   * @return The vector pointing to the origin of the potential field.
   */
  INDArray getOrigin();
}
