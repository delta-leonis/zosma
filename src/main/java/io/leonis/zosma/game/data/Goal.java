package io.leonis.zosma.game.data;

import io.leonis.algieba.Spatial;
import io.leonis.algieba.geometry.Vectors;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface Goal.
 *
 * This interface describes the state of a goal.
 *
 * @author Jeroen de Jong
 */
public interface Goal extends Spatial {

  /**
   * @return The field half this goal is located.
   */
  FieldHalf getFieldHalf();

  /**
   * @return The dimension of the goal.
   */
  GoalDimension getDimension();

  /**
   * @return The allegiance of the team defending this goal.
   */
  Allegiance getAllegiance();

  /**
   * @return The X-position coordinate of the {@link Goal}.
   */
  default double getX() {
    return this.getPosition().getDouble(0, 0);
  }

  /**
   * @return The Y-position coordinate of the {@link Goal}.
   */
  default double getY() {
    return this.getPosition().getDouble(1, 0);
  }

  @Value
  class State implements Goal {
    private final GoalDimension dimension;
    private final FieldHalf fieldHalf;
    private final Allegiance allegiance;
    private final double fieldLength;

    @Override
    public INDArray getPosition() {
      return fieldHalf.equals(FieldHalf.NEGATIVE) ?
          Vectors.columnVector((fieldLength + getDimension().getDepth()) / 2f, 0f)
          : Vectors.columnVector(-1f * ((fieldLength + getDimension().getDepth()) / 2f), 0f);
    }
  }
}