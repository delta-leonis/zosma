package io.leonis.zosma.game.engine;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class PlayersVelocityFunction.
 *
 * This class represents a {@link Function} which calculates velocities of {@link Player}.
 *
 * @author Rimon Oz
 */
public class PlayersVelocityFunction implements BiFunction<Set<Player>, Set<Player>, Set<MovingPlayer>> {

  @Override
  public Set<MovingPlayer> apply(final Set<Player> currentPlayers, final Set<Player> previousPlayers) {
    return currentPlayers.stream()
        .map(currentPlayer ->
            previousPlayers.stream()
                .filter(previousPlayer ->
                    previousPlayer.getIdentity().equals(currentPlayer.getIdentity()))
                .findFirst()
                .map(previousPlayer ->
                    new MovingPlayer.State(currentPlayer.getId(),
                        currentPlayer.getTimestamp(),
                        currentPlayer.getX(),
                        currentPlayer.getY(),
                        currentPlayer.getOrientation(),
                        (currentPlayer.getX() - previousPlayer.getX())
                            / (currentPlayer.getTimestamp() - previousPlayer.getTimestamp()),
                        (currentPlayer.getY() - previousPlayer.getY())
                            / (currentPlayer.getTimestamp() - previousPlayer.getTimestamp()),
                        (currentPlayer.getOrientation() - previousPlayer.getOrientation())
                            / (currentPlayer.getTimestamp() - previousPlayer.getTimestamp()),
                        currentPlayer.getAllegiance()))
                .orElse(
                    new MovingPlayer.State(
                        currentPlayer.getId(),
                        currentPlayer.getTimestamp(),
                        currentPlayer.getX(),
                        currentPlayer.getY(),
                        currentPlayer.getOrientation(),
                        currentPlayer.getAllegiance())))
        .collect(Collectors.toSet());
  }
}
