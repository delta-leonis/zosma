package io.leonis.zosma.game.data;

import java.io.Serializable;
import lombok.Value;

/**
 * The Class FieldLineState.
 *
 * This class represents a line on a {@link io.leonis.zosma.game.data.Field}.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 * @author Ryan Meulenkamp
 */
public interface FieldLine extends Serializable {

  /**
   * @return The x-coordinate of the starting point of the line in mm.
   */
  double getXStart();

  /**
   * @return The y-coordinate of the starting point of the line in mm.
   */
  double getYStart();

  /**
   * @return The x-coordinate of the ending point of the line in mm.
   */
  double getXEnd();

  /**
   * @return The y-coordinate of the ending point of the line in mm.
   */
  double getYEnd();

  /**
   * @return The thickness of the line in mm.
   */
  double getThickness();

  @Value
  class State implements FieldLine {
    private double xStart, yStart, xEnd, yEnd, thickness;
  }
}
