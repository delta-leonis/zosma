package io.leonis.zosma.math;

import io.leonis.zosma.game.data.PlayerCommand;
import lombok.experimental.Delegate;

/**
 * The Class MultiplyPlayerCommand.
 *
 * This class represents the computation of scalar multiplication on a {@link PlayerCommand}.
 *
 * @author Rimon Oz
 */
public class MultiplyPlayerCommand implements PlayerCommand {
  @Delegate
  private final PlayerCommand playerCommand;

  /**
   * Computes the scalar product of the supplied {@link PlayerCommand} and multiplier.
   *
   * @param leftCommand The {@link PlayerCommand} to multiply.
   * @param multiplier  The scalar multiplier.
   */
  public MultiplyPlayerCommand(final PlayerCommand leftCommand, final float multiplier) {
    this.playerCommand = new PlayerCommand.State(
        leftCommand.getVelocityX() * multiplier,
        leftCommand.getVelocityY() * multiplier,
        leftCommand.getVelocityR() * multiplier,
        leftCommand.getFlatKick() * multiplier,
        leftCommand.getChipKick() * multiplier,
        leftCommand.getDribblerSpin() * multiplier);
  }
}
