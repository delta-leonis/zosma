package io.leonis.zosma.game.engine;

import io.leonis.algieba.spatial.*;
import io.leonis.zosma.game.data.*;
import java.util.function.*;
import java.util.stream.*;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class PotentialFieldDeducer.
 *
 * @author Rimon Oz
 */
@Value
public class PotentialFieldDeducer<G extends MovingPlayer.SetSupplier & Field.Supplier & MovingBall.SetSupplier>
    implements Deducer<G, Strategy.Supplier> {
  private final TeamColor teamColor;
  private final INDArray origin;
  private final Function<MovingPlayer, PotentialField> playerFieldGenerator;
  private final Function<MovingBall, PotentialField> ballFieldGenerator;
  private final BiFunction<MovingPlayer, PotentialField, PlayerCommand> commandGenerator;

  @Override
  public Publisher<Strategy.Supplier> apply(
      final Publisher<G> inputPublisher
  ) {
    return Flux.from(inputPublisher)
        .map(game -> {
          final AggregatedPotentialField aggregatedPotentialField = new AggregatedPotentialField(
              this.origin,
              Stream.concat(
                  game.getPlayers().stream().map(this.playerFieldGenerator),
                  game.getBalls().stream().map(this.ballFieldGenerator))
                  .collect(Collectors.toSet()));

          return () -> game.getPlayers().stream()
              .filter(player -> player.getTeamColor().equals(this.getTeamColor()))
              .collect(Collectors.toMap(
                  Player::getIdentity,
                  player -> this.commandGenerator.apply(player, aggregatedPotentialField)));
        });
  }
}
