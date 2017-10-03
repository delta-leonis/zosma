package io.leonis.math.spatial;

import java.util.Set;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class AggregatedPotentialField.
 *
 * This class represents an aggregation of multiple {@link PotentialField}.
 *
 * @author Rimon Oz
 */
@Value
public class AggregatedPotentialField implements PotentialField {
  private final INDArray origin;
  private final Set<PotentialField> potentialFields;

  @Override
  public INDArray getPotential(final INDArray positionVector) {
    return this.getPotentialFields().stream()
        .map(potentialField -> potentialField.getPotential(positionVector))
        .reduce(Nd4j.zeros(1, 1), INDArray::add);
  }

  @Override
  public INDArray getForce(final INDArray positionVector) {
    return this.getPotentialFields().stream()
        .map(potentialField -> potentialField.getForce(positionVector))
        .reduce(INDArray::add)
        .orElse(Nd4j.zeros(positionVector.shape()));
  }
}
