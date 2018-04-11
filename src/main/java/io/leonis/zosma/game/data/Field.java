package io.leonis.zosma.game.data;

import io.leonis.zosma.game.data.Goal.*;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import java.io.Serializable;
import java.util.Set;
import lombok.*;

/**
 * The Interface Field.
 *
 * This interface describes the functionality of a field.
 *
 * @author Rimon Oz
 */
public interface Field extends Serializable {

  /**
   * @return The width of the field in mm.
   */
  double getWidth();

  /**
   * @return The length of the field in mm.
   */
  double getLength();

  /**
   * @return A {@link Set} of {@link FieldLine lines}.
   */
  Set<FieldLine> getLines();

  /**
   * @return A {@link Set} of {@link FieldArc arcs}.
   */
  Set<FieldArc> getArcs();

  Goal getPositiveGoal();

  Goal getNegativeGoal();

  default Goal getGoal(FieldHalf fieldHalf) {
    return fieldHalf.equals(FieldHalf.POSITIVE) ? getPositiveGoal() : getNegativeGoal();
  }

  @Value
  @AllArgsConstructor
  class State implements Field {
    private final double width;
    private final double length;
    private final Set<FieldLine> lines;
    private final Set<FieldArc> arcs;
    private final GoalDimension goalDimension;

    @Override
    public Goal getPositiveGoal() {
      return new PositiveGoal(this, goalDimension);
    }

    @Override
    public Goal getNegativeGoal() {
      return new NegativeGoal(this, goalDimension);
    }
  }
}
