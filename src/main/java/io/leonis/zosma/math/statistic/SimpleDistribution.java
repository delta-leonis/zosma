package io.leonis.zosma.math.statistic;

import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class SimpleDistribution.
 *
 * This class describes a simple distribution, that is a statistical distribution with a mean and a
 * covariance.
 *
 * @author Rimon Oz
 */
@Value
public class SimpleDistribution implements Distribution {
  private final INDArray mean;
  private final INDArray covariance;
}
