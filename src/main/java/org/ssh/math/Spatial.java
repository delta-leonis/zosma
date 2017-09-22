package org.ssh.math;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class Spatial.
 *
 * This class represents an object (in an n-dimensional space) which has a position vector.
 *
 * @author Rimon Oz
 */
public interface Spatial {

  /**
   * @return The position vector of the stationary object.
   */
  INDArray getPosition();
}
