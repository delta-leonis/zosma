package io.leonis.zosma.math;

import io.leonis.zosma.game.data.PlayerCommand;
import lombok.experimental.Delegate;

/**
 * The Class LimitPlayerCommand.
 *
 * This class represents the computation of limiting the velocity in a {@link PlayerCommand}.
 *
 * @author Rimon Oz
 */
public class LimitPlayerCommand implements PlayerCommand {
  @Delegate
  private final PlayerCommand playerCommand;

  /**
   * Computes the {@link PlayerCommand} as a result of limiting the velocity vector by the supplied
   * speed limit.
   *
   * @param command    The {@link PlayerCommand} to limit.
   * @param speedLimit The speed limit.
   */
  public LimitPlayerCommand(final PlayerCommand command, final float speedLimit) {
    final double velocityMagnitude
        = Math.sqrt(Math.pow(command.getVelocityX(), 2)
        + Math.pow(command.getVelocityX(), 2));
    this.playerCommand = new PlayerCommand.State(
        (float) (speedLimit / velocityMagnitude
            * Math.tanh(command.getVelocityX())),
        (float) (speedLimit / velocityMagnitude
            * Math.tanh(command.getVelocityY())),
        (float) (speedLimit / velocityMagnitude
            * Math.tanh(command.getVelocityR())),
        command.getFlatKick(),
        command.getChipKick(),
        command.getDribblerSpin());
  }
}
