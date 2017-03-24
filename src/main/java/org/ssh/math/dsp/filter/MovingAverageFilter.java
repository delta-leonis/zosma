package org.ssh.math.dsp.filter;

import java.util.List;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Interface MovingAverageFilter.
 *
 * @author Rimon Oz
 */
public interface MovingAverageFilter extends Filter<INDArray> {

  /**
   * Apply ind array.
   *
   * @param buffer the buffer
   * @return ind array
   */
  static INDArray apply(
      List<INDArray> buffer
  ) {
    return buffer.stream()
        .reduce(INDArray::add)
        .orElse(Nd4j.zeros(0))
        .div(buffer.size());
  }
}
