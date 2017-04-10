package org.ssh.math.geometry;

import lombok.experimental.UtilityClass;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class Rotation.
 *
 * @author Rimon Oz
 */
@UtilityClass
public class Rotation {

  /**
   * Planar cartesian ind array.
   *
   * @param input    the input
   * @param rotation the rotation
   * @return the ind array
   */
  public static INDArray planarCartesian(INDArray input, double rotation) {
    return Nd4j.create(
        new double[]{
            StrictMath.cos(rotation), -1d * StrictMath.sin(rotation),
            StrictMath.sin(rotation), StrictMath.cos(rotation)
        },
        new int[]{2, 2}).mmul(input);
  }
}
