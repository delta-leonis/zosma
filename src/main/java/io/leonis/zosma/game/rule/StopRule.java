package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import java.util.Set;
import java.util.stream.Collectors;
import org.nd4j.linalg.indexing.NDArrayIndex;

/**
 * The Class StopRule.
 *
 * This rule determines whether {@link Player robots}are keeping at least 50cm from the ball.
 *
 * @author Rimon Oz
 */
public class StopRule<I extends Player.SetSupplier & Ball.SetSupplier>
    implements Rule<I, Player> {

  @Override
  public Set<Player> getViolators(final I input) {
    return input.getPlayers().stream()
        .filter(player -> this.test(input, player))
        .collect(Collectors.toSet());
  }

  /**
   * @param ballsSupplier The game state object.
   * @param player        The {@link Player robot} to verify whether it is at least 50cm from the
   *                      ball.
   * @return True if the {@link Player robot} robot is at least 50cm from the ball, false otherwise.
   */
  public boolean test(final Ball.SetSupplier ballsSupplier, final Player player) {
    return ballsSupplier.getBalls().stream()
        .anyMatch(ball ->
            ball.getPosition()
                .get(NDArrayIndex.interval(0, 2), NDArrayIndex.all())
                .distance2(
                    player.getPosition().get(NDArrayIndex.interval(0, 2), NDArrayIndex.all()))
                < 500d);
  }

  @Override
  public boolean test(final I input) {
    return input.getPlayers().stream()
        .anyMatch(player -> this.test(input, player));
  }
}
