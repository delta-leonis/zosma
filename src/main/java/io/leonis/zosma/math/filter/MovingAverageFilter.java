package io.leonis.zosma.math.filter;

import java.util.List;
import java.util.function.Function;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Interface MovingAverageFilter.
 *
 * @author Rimon Oz
 */
public class MovingAverageFilter implements Function<List<INDArray>, INDArray> {

  /**
   * @param buffer The buffer to average.
   * @return The average of the {@link INDArray} in the buffer.
   */
  @Override
  public INDArray apply(
      final List<INDArray> buffer
  ) {
    return buffer.stream()
        .reduce(INDArray::add)
        .orElse(Nd4j.zeros(0))
        .div(buffer.size());
  }
}
