package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class HaltRule.
 *
 * @author Rimon Oz
 */
public class HaltRule implements Rule<MovingPlayer.SetSupplier, MovingPlayer> {

  @Override
  public Set<MovingPlayer> getViolators(final MovingPlayer.SetSupplier playerSupplier) {
    return playerSupplier.getPlayers().stream()
        .filter(this::test)
        .collect(Collectors.toSet());
  }

  @Override
  public boolean test(final MovingPlayer.SetSupplier playerSupplier) {
    return playerSupplier.getPlayers().stream()
        .anyMatch(this::test);
  }

  /**
   * @param player The {@link Player} to verify whether it has stopped.
   * @return True if it has stopped, false otherwise.
   */
  public boolean test(final MovingPlayer player) {
    return player.getVelocity().norm2Number().doubleValue() < 0.001d;
  }
}
