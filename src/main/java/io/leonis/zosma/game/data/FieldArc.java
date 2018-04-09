package io.leonis.zosma.game.data;

import java.io.Serializable;
import lombok.Value;

/**
 * The Class FieldArcState.
 *
 * This class represents an arced line on a {@link io.leonis.zosma.game.data.Field}.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 * @author Ryan Meulenkamp
 */
public interface FieldArc extends Serializable {

  /**
   * @return The x-coordinate of the center of the arced line in mm.
   */
  double getX();

  /**
   * @return The y-coordinate of the center of the arced line in mm.
   */
  double getY();

  /**
   * @return The starting angle of the arced line in radians.
   */
  double getStartAngle();

  /**
   * @return The stopping angle of the arced line in radians.
   */
  double getStopAngle();

  /**
   * @return The thickness of the arced line in mm.
   */
  double getThickness();

  /**
   * @return The radius of the arced line in mm.
   */
  double getRadius();

  @Value
  class State implements FieldArc {
    private double x, y, startAngle, stopAngle, thickness, radius;
  }
}
