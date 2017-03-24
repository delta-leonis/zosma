package org.ssh.math.statistics;

import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Created by romnous on 3/19/17.
 */
@Value
public class SimpleDistribution implements Distribution<INDArray> {
    private final INDArray mean;
    private final INDArray covariance;
}
