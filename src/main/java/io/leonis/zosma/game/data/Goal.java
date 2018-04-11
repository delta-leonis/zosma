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

  FieldHalf getFieldHalf();

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

  class PositiveGoal implements Goal {
    @Delegate
    private final Goal.State goal;

    public PositiveGoal(final Field field, final GoalDimension goalDimension) {
      this.goal = new Goal.State(
          goalDimension,
          Vectors.columnVector((field.getLength() + goalDimension.getDepth()) / 2f, 0f),
          FieldHalf.POSITIVE);
    }
  }

  class NegativeGoal implements Goal {
    @Delegate
    private final Goal.State goal;

    public NegativeGoal(final Field field, final GoalDimension goalDimension) {
      this.goal = new Goal.State(
          goalDimension,
          Vectors.columnVector(-1f * ((field.getLength() + goalDimension.getDepth()) / 2f), 0f),
          FieldHalf.NEGATIVE);
    }
  }
}
