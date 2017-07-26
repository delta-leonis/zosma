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
   * @param input    The input vector.
   * @param rotation The rotation (in radians).
   * @return The vector rotated by the specified rotation.
   */
  public static INDArray planarCartesian(final INDArray input, final double rotation) {
    return Nd4j.create(
        new double[]{
            StrictMath.cos(rotation), -1d * StrictMath.sin(rotation),
            StrictMath.sin(rotation), StrictMath.cos(rotation)
        },
        new int[]{2, 2}).mmul(input);
  }
}
