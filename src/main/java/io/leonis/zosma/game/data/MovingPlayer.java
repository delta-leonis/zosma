package io.leonis.zosma.game.data;

import io.leonis.algieba.spatial.Moving;
import io.leonis.algieba.statistic.*;
import java.util.Set;
import lombok.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

/**
 * The Interface MovingPlayer.
 *
 * This interface represents a {@link Moving} {@link Player robot} in a Small Size League game.
 *
 * @author Rimon Oz
 */
public interface MovingPlayer extends Player, Moving {

  /**
   * @return The X-velocity coordinate of the {@link Player robot} in mm / us.
   */
  default double getXVelocity() {
    return this.getState().getMean().getDouble(4, 0);
  }

  /**
   * @return The Y-velocity coordinate of the {@link Player robot} in mm / us.
   */
  default double getYVelocity() {
    return this.getState().getMean().getDouble(5, 0);
  }

  /**
   * @return The orientation velocity of the {@link Player robot} in mm / us.
   */
  default double getOrientationVelocity() {
    return this.getState().getMean().getDouble(6, 0);
  }

  /**
   * @return The XY-velocity of the {@link Player}.
   */
  default INDArray getXYVelocity() {
    return this.getState().getMean().get(NDArrayIndex.interval(4, 6), NDArrayIndex.all());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  default INDArray getVelocity() {
    return this.getState().getMean().get(NDArrayIndex.interval(4, 7), NDArrayIndex.all());
  }

  /**
   * Represents the functionality of an object which can supply a {@link Set} of {@link
   * MovingPlayer}.
   */
  interface SetSupplier {
    Set<MovingPlayer> getPlayers();
  }

  /**
   * Represents the calculated state of a {@link MovingPlayer}.
   */
  @Value
  @AllArgsConstructor
  class State implements MovingPlayer {
    private final int id;
    private final Distribution state;
    private final TeamColor teamColor;

    /**
     * Creates an state representation of the current {@link Player} with velocity data.
     *
     * @param currentPlayer  The current state of the {@link Player}.
     * @param previousPlayer The previous state of the {@link Player}.
     */
    public State(final Player currentPlayer, final Player previousPlayer) {
      this(currentPlayer.getId(),
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
          currentPlayer.getTeamColor());
    }

    public State(
        final int id,
        final double timestamp,
        final double x,
        final double y,
        final double orientation,
        final double velocityX,
        final double velocityY,
        final double velocityR,
        final TeamColor teamColor
    ) {
      this(
          id,
          new SimpleDistribution(Nd4j.create(
              new double[]{
                  timestamp,
                  x,
                  y,
                  orientation,
                  velocityX,
                  velocityY,
                  velocityR
              },
              new int[]{7, 1}), Nd4j.eye(7)),
          teamColor);

    }
  }
}
