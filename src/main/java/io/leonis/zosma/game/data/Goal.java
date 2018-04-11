package io.leonis.zosma.game.data;

import io.leonis.algieba.Spatial;
import io.leonis.algieba.geometry.Vectors;
import lombok.Value;
import lombok.experimental.Delegate;
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
    private final INDArray position;
    private final FieldHalf fieldHalf;
  }

  /**
   * A Goal on the positive half of the field.
   */
  class PositiveHalfGoal implements Goal {
    @Delegate
    private final Goal.State goal;

    public PositiveHalfGoal(double fieldLength, final GoalDimension goalDimension) {
      this.goal = new Goal.State(
          goalDimension,
          Vectors.columnVector((fieldLength + goalDimension.getDepth()) / 2f, 0f),
          FieldHalf.POSITIVE);
    }
  }

  /**
   * A Goal on the negative half of the field.
   */
  class NegativeHalfGoal implements Goal {
    @Delegate
    private final Goal.State goal;

    public NegativeHalfGoal(final double fieldLength, final GoalDimension goalDimension) {
      this.goal = new Goal.State(
          goalDimension,
          Vectors.columnVector(-1f * ((fieldLength + goalDimension.getDepth()) / 2f), 0f),
          FieldHalf.NEGATIVE);
    }
  }
}
