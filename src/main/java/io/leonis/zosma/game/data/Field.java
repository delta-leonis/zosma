package io.leonis.zosma.game.data;

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

  @Value
  @AllArgsConstructor
  class State implements Field {
    private final double width;
    private final double length;
    private final Set<FieldLine> lines;
    private final Set<FieldArc> arcs;
  }
}
