package io.leonis.zosma.ipc.peripheral.component;

import lombok.*;

/**
 * The class Stick.
 *
 * Describes a stick on a typical controller.
 *
 * @author Jeroen de Jong
 */
@Value
@AllArgsConstructor
public class Stick {

  /**
   * The X position of the stick between -1 and 1
   */
  private final float x;

  /**
   * The Y position of the stick between -1 and 1
   */
  private final float y;

  /**
   * The angle of the stick (for reference, 0 is right, 90 is up, 180 is left, 270 is down)
   */
  private final double angle;

  /**
   * The amount the stick is pushed in the current direction. This probably between 0 and 1
   */
  private final double magnitude;

  /**
   * True if pressed
   */
  private final boolean pressed;

  /**
   * Creates a stick but calculates the magnitude and angle.
   *
   * @param x       Position between -1 and 1.
   * @param y       Position between -1 and 1.
   * @param pressed True if pressed, false otherwise.
   */
  public Stick(final float x, final float y, final boolean pressed) {
    this(x, y,
        Math.toDegrees(Math.atan2(y, x)),
        Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)),
        pressed);
  }

  /**
   * The Interface LeftSupplier.
   *
   * Supplies state of the left stick.
   *
   * @author Jeroen de Jong
   */
  public interface LeftSupplier {

    /**
     * @return The left stick.
     */
    Stick getLeftStick();
  }

  /**
   * The Interface RightSupplier.
   *
   * Supplies state of the right stick.
   *
   * @author Jeroen de Jong
   */
  public interface RightSupplier {

    /**
     * @return The right stick.
     */
    Stick getRightStick();
  }

  /**
   * The Interface Supplier.
   *
   * Supplies state of both the left and right stick.
   *
   * @author Jeroen de Jong
   */
  public interface Supplier extends LeftSupplier, RightSupplier {

  }
}
