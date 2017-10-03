package io.leonis.math.statistic.distribution;


import io.leonis.math.statistic.Distribution;
import java.util.Collection;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class GaussianDistribution.
 *
 * This class represents the statistics of a state-space which is characterized by a mean and
 * covariance such that the distribution is of the Gaussian form. This distribution is often called
 * the normal distribution.
 *
 * @author Rimon Oz
 */
@Value
public class GaussianDistribution implements Distribution {

  /**
   * The Gaussian mean of the distribution.
   **/
  private final INDArray mean;
  /**
   * The Gaussian covariance of the distribution.
   **/
  private final INDArray covariance;

  /**
   * Calculates the value of the Gaussian error function. See <a href="http://www.ams.org/journals/mcom/1969-23-107/S0025-5718-1969-0247736-4/S0025-5718-1969-0247736-4.pdf">
   * here</a> for implementation details. The value of this function may cancel when extremely close
   * to 0.
   *
   * @param value The value for which the Gaussian error function needs to be calculated.
   * @return The value of the Gaussian error function for the supplied parameter.
   */
  public static double erf(final double value) {
    final double t = 1.0 / (1.0 + 0.5 * StrictMath.abs(value));

    // Horner's method
    final double result = 1 - t
        * StrictMath.exp(-value * value - 1.26551223 + t
        * (1.00002368 + t
        * (0.37409196 + t
        * (0.09678418 + t
        * (-0.18628806 + t
        * (0.27886807 + t
        * (-1.13520398 + t
        * (1.48851587 + t
        * (-0.82215223 + t
        * (0.17087277))))))))));

    return (value >= 0) ? result : -result;
  }

  /**
   * @param samples The samples to compute a Gaussian distribution from.
   * @return The Gaussian distribution representing the supplied samples.
   */
  public static Distribution from(final Collection<INDArray> samples) {
    return new GaussianDistribution(
        calculateMean(samples),
        calculateCovariance(samples));
  }

  /**
   * @param collection The {@link Collection} to calculate the mean for as an {@link INDArray}.
   * @return The Gaussian mean of the supplied {@link Collection} as an {@link INDArray}.
   */
  public static INDArray calculateMean(final Collection<INDArray> collection) {
    final int dimension = collection.iterator().next().rows();

    return collection.stream()
        .reduce(Nd4j.zeros(dimension, 1), INDArray::add)
        .div(collection.size());
  }

  /**
   * @param collection The {@link Collection} to calculate the covariance for as an {@link
   *                   INDArray}.
   * @return The Gaussian covariance of the supplied {@link Collection} as an {@link INDArray}.
   */
  public static INDArray calculateCovariance(final Collection<INDArray> collection) {
    final int dimension = collection.iterator().next().rows();

    final INDArray sampleMean = GaussianDistribution.calculateMean(collection);

    return collection.stream()
        .reduce(Nd4j.zeros(dimension, dimension),
            (total, sample) ->
                total.add(
                    sample.sub(sampleMean)
                        .mul(sample.sub(sampleMean).transpose())))
        .div(collection.size() - 1);
  }

  /**
   * @param mean       The mean of the Gaussian distribution.
   * @param covariance The covariance of the Gaussian distribution.
   * @return A {@link Distribution} with the supplied mean and covariance.
   */
  public static Distribution from(final INDArray mean, final INDArray covariance) {
    return new GaussianDistribution(mean, covariance);
  }
}
