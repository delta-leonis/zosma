package io.leonis.math.statistic;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class Distribution.
 *
 * This class represents the statistics of a state-space which is characterized by a mean and a
 * covariance.
 *
 * @author Rimon Oz
 */
public interface Distribution {

  /**
   * @return The mean of the distribution.
   */
  INDArray getMean();

  /**
   * @return The covariance of the distribution.
   */
  INDArray getCovariance();
}
