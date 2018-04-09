package io.leonis.zosma.game.data;

import java.io.Serializable;
import lombok.*;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface PlayerCommand.
 *
 * This interface describes the functionality of a command which is issued to an agent.
 *
 * @author Rimon Oz
 */
public interface PlayerCommand extends Serializable {
  /** This command will stop the robot. */
  PlayerCommand STOP = new PlayerCommand.State(0, 0, 0, 0, 0, 0);

  /**
   * @return Desired forward drive velocity in mm per second.
   */
  float getVelocityX();

  /**
   * @return Desired sideways left drive velocity in mm per second.
   */
  float getVelocityY();

  /**
   * @return Desired counter-clockwise angular velocity in radians per second.
   */
  float getVelocityR();

  /**
   * If this field is empty then no flat kick command should be sent.
   *
   * @return Desired flat kick speed, in meters per second.
   */
  float getFlatKick();

  /**
   * Desired chip kick distance in meters. If this field is missing then no flat kick command should
   * be sent. If this field is present along with the flat_kick field then the flat_kick command
   * takes precedence.
   *
   * @return Desired chip kick distance in meters.
   */
  float getChipKick();

  /**
   * Desired dribbler spin, from -1 to +1, where -1 is the maximum reverse-spin that can be imparted
   * to the ball, and +1 is the maximum forward-spin that can be imparted to the ball.
   *
   * @return Desired dribbler spin, from -1 to +1.
   */
  float getDribblerSpin();

  @Value
  @AllArgsConstructor
  class State implements PlayerCommand {
    private final float velocityX;
    private final float velocityY;
    private final float velocityR;
    private final float flatKick;
    private final float chipKick;
    private final float dribblerSpin;

    /**
     * @param velocityVector The velocity vector to extract the velocity components from.
     * @param flatKick       The flat-kick strength as a percentage (between 0 and 1).
     * @param chipKick       The chip-kick strength as a percentage (between 0 and 1).
     * @param dribblerSpin   The dribbler spin as a percentage with the sign indicating the
     *                       direction of spin (between -1 and 1)
     */
    public State(
        final INDArray velocityVector,
        final float flatKick,
        final float chipKick,
        final float dribblerSpin
    ) {
      this(
          velocityVector.getFloat(0, 0),
          velocityVector.getFloat(1, 0),
          velocityVector.getFloat(2, 0),
          flatKick,
          chipKick,
          dribblerSpin);
    }
  }
}
