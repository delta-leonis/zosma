package org.ssh.math.geometry;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface Norm.
 *
 * This interface describes the functionality of a norm, ie. a measure of distance.
 *
 * @author Rimon Oz
 */
public interface Norm {

  /**
   * @param input The vector to compute the norm of.
   * @return The norm of the vector.
   */
  double calculateNorm(final INDArray input);
}
