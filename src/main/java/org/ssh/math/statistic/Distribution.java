package org.ssh.math.statistic;

/**
 * The Class Distribution.
 * <p>
 * This class represents the statistics of a state-space which is characterized by a mean and
 * a covariance.
 *
 * @param <V> The type of vector in the space which contains the distribution.
 * @author Rimon Oz
 */
public interface Distribution<V> {

  /**
   * @return The mean of the distribution.
   */
  V getMean();

  /**
   * @return The covariance of the distribution.
   */
  V getCovariance();
}
