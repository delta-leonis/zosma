package io.leonis.zosma.game.data;

import java.io.Serializable;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface GoalDimension.
 *
 * This interface describes the dimension of a goal.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 * @author Ryan Meulenkamp
 */
public interface GoalDimension extends Serializable {

  /**
   * @return The width of the goal in mm.
   */
  default double getWidth() {
    return this.getState().getDouble(0, 0);
  }

  /**
   * @return The {@link INDArray state vector} of the goal.
   */
  INDArray getState();

  /**
   * @return The depth of the goal in mm.
   */
  default double getDepth() {
    return this.getState().getDouble(1, 0);
  }

  @Value
  class State implements GoalDimension {
    private final INDArray state;
  }
}
