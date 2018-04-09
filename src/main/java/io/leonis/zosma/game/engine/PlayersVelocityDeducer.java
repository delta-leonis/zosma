package io.leonis.zosma.game.engine;

import io.leonis.zosma.game.data.*;
import java.util.*;
import java.util.stream.Collectors;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class PlayersVelocityDeducer.
 *
 * This class represents a {@link Deducer} which calculates velocities of {@link Player}.
 *
 * @param <I> The type of state carrying a {@link Set} of {@link Player}.
 * @author Rimon Oz
 */
public class PlayersVelocityDeducer<I extends Player.SetSupplier>
    implements Deducer<I, Set<MovingPlayer>> {
  @Override
  public Publisher<Set<MovingPlayer>> apply(final Publisher<I> iPublisher) {
    return Flux.from(iPublisher)
        .scan(
            Collections.emptySet(),
            (previousGame, currentGame) ->
                currentGame.getPlayers().stream()
                    .map(currentPlayer ->
                        previousGame.stream()
                            .filter(previousPlayer ->
                                previousPlayer.getIdentity().equals(currentPlayer.getIdentity()))
                            .findFirst()
                            .map(previousPlayer ->
                                new MovingPlayer.State(currentPlayer, previousPlayer))
                            .orElse(
                                new MovingPlayer.State(
                                    currentPlayer.getId(),
                                    currentPlayer.getTimestamp(),
                                    currentPlayer.getX(),
                                    currentPlayer.getY(),
                                    currentPlayer.getOrientation(),
                                    0d,
                                    0d,
                                    0d,
                                    currentPlayer.getTeamColor())))
                    .collect(Collectors.toSet()));
  }
}
