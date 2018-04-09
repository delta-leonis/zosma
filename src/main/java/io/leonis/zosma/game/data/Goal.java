package io.leonis.zosma.game.data;

import io.leonis.algieba.Spatial;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import java.util.Set;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface Goal.
 *
 * This interface describes the state of a goal.
 *
 * @author Jeroen de Jong
 */
public interface Goal extends GoalDimension.Supplier, Spatial, FieldHalf.Supplier {

  /**
   * @return The {@link TeamIdentity} defending to this goal.
   */
  TeamIdentity getTeamIdentity();

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

  interface SetSupplier {
    Set<Goal> getGoals();
  }

  @Value
  class State implements Goal {
    private final GoalDimension goalDimension;
    private final INDArray position;
    private final TeamIdentity teamIdentity;
    private final FieldHalf fieldHalf;
  }
}
