package org.ssh.math.statistic;

import java.util.stream.IntStream;
import lombok.Value;
import lombok.experimental.Delegate;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class GaussianHistogram.
 *
 * This class represents a {@link Histogram} with a Gaussian {@link Distribution} imposed
 * on it.
 *
 * @author Rimon Oz
 */
@Value
public class GaussianHistogram implements Histogram, Distribution {

  @Delegate
  private final Histogram simpleHistogram;

  @Override
  public INDArray getMean() {
    return Nd4j.create(new double[]{
        IntStream.range(0, this.getBins().size())
            .mapToDouble(index -> this.getBins().get(index) * this.computeAverageValue(index))
            .average()
            .orElse(0d)});
  }

  @Override
  public INDArray getCovariance() {
    final INDArray mean = this.getMean();
    return Nd4j.create(new double[]{IntStream.range(0, this.getBins().size())
        .mapToDouble(index -> this.getBins().get(index) * this.computeAverageValue(index))
        .map(sample -> Math.pow(sample - mean.getDouble(0, 0), 2))
        .average()
        .orElse(0d)});
  }
}
