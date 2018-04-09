package io.leonis.zosma.game.engine;

import io.leonis.algieba.spatial.*;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Strategy;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import io.reactivex.functions.Function3;
import java.util.Set;
import java.util.stream.*;
import lombok.AllArgsConstructor;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class PotentialFieldFunction.
 *
 * @author Rimon Oz
 */
@AllArgsConstructor
public final class PotentialFieldFunction
    implements Function3<Set<MovingPlayer>, Set<MovingBall>, Field, Strategy> {

  private final TeamIdentity teamIdentity;
  private final INDArray origin;
  private final java.util.function.Function<MovingPlayer, PotentialField> playerFieldGenerator;
  private final java.util.function.Function<MovingBall, PotentialField> ballFieldGenerator;
  private final java.util.function.BiFunction<MovingPlayer, PotentialField, PlayerCommand> commandGenerator;

  @Override
  public Strategy apply(final Set<MovingPlayer> players, final Set<MovingBall> balls,
      final Field field) {
    return () -> players.stream()
        .filter(player -> player.getTeamIdentity().equals(teamIdentity))
        .collect(Collectors.toMap(
            Player::getIdentity,
            player -> this.commandGenerator.apply(player,
                new AggregatedPotentialField(
                    this.origin,
                    Stream.concat(
                        players.stream().map(this.playerFieldGenerator),
                        balls.stream().map(this.ballFieldGenerator))
                        .collect(Collectors.toSet())))));
  }
}
