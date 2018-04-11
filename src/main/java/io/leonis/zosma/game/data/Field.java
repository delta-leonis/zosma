package io.leonis.zosma.game.data;

import io.leonis.zosma.game.data.Goal.*;
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

  /**
   * @param fieldHalf field half the goal is on.
   * @return The {@link Goal} on the specified field half.
   */
  Goal getGoal(FieldHalf fieldHalf);

  @Value
  @AllArgsConstructor
  class State implements Field {
    private final double width;
    private final double length;
    private final Set<FieldLine> lines;
    private final Set<FieldArc> arcs;
    private final Goal positiveHalfGoal, negativeHalfGoal;

    public State(double width, double length, Set<FieldLine> lines, Set<FieldArc> arcs, GoalDimension dimension) {
      this(width, length, lines, arcs,
        new PositiveHalfGoal(length, dimension),
        new NegativeHalfGoal(length, dimension));
    }

    @Override
    public Goal getGoal(final FieldHalf fieldHalf) {
      return fieldHalf.equals(FieldHalf.POSITIVE) ? positiveHalfGoal : negativeHalfGoal;
    }
  }
}
