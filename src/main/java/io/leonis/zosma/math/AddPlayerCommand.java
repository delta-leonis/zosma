package io.leonis.zosma.math;

import io.leonis.zosma.game.data.PlayerCommand;
import java.util.*;
import lombok.experimental.Delegate;

/**
 * The Class AddPlayerCommand.
 *
 * This class represents the computation of addition of two {@link PlayerCommand}.
 *
 * @author Rimon Oz
 */
public class AddPlayerCommand implements PlayerCommand {
  @Delegate
  private final PlayerCommand playerCommand;

  /**
   * Computes the addition of the supplied more than two {@link PlayerCommand}.
   *
   * @param leftCommand   The first command to add.
   * @param rightCommands The remaining commands to add.
   */
  public AddPlayerCommand(final PlayerCommand leftCommand, final Collection<PlayerCommand> rightCommands) {
    this.playerCommand = rightCommands.stream()
        .reduce(leftCommand,
            (left, right) ->
                new PlayerCommand.State(
                    left.getVelocityX() + right.getVelocityX(),
                    left.getVelocityY() + right.getVelocityY(),
                    left.getVelocityR() + right.getVelocityR(),
                    left.getFlatKick() + right.getFlatKick(),
                    left.getChipKick() + right.getChipKick(),
                    left.getDribblerSpin() + right.getDribblerSpin()));
  }

  /**
   * Computes the addition of the supplied more than two {@link PlayerCommand}.
   *
   * @param leftCommand   The first command to add.
   * @param rightCommands The remaining commands to add.
   */
  public AddPlayerCommand(final PlayerCommand leftCommand, final PlayerCommand... rightCommands) {
    this(leftCommand, Arrays.asList(rightCommands));
  }
}
