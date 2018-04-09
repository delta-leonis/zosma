package io.leonis.zosma.math;

import io.leonis.zosma.game.data.PlayerCommand;
import java.util.*;
import lombok.experimental.Delegate;

/**
 * The Class AveragePlayerCommand.
 *
 * This class represents the computation of the average of a collection of {@link PlayerCommand}.
 *
 * @author Rimon Oz
 */
public class AveragePlayerCommand implements PlayerCommand {
  @Delegate
  private final PlayerCommand playerCommand;

  /**
   * Computes the average of the supplied {@link PlayerCommand commands}.
   *
   * @param commands The {@link PlayerCommand commands} to compute the average of.
   */
  public AveragePlayerCommand(final PlayerCommand... commands) {
    this(Arrays.asList(commands));
  }
  /**
   * Computes the average of the supplied {@link PlayerCommand commands}.
   *
   * @param commands The {@link PlayerCommand commands} to compute the average of.
   */
  public AveragePlayerCommand(final Collection<PlayerCommand> commands){
    this.playerCommand = new MultiplyPlayerCommand(
        new AddPlayerCommand(PlayerCommand.State.STOP, commands),
        1f / commands.size());
  }
}
