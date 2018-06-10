package io.leonis.zosma.game.data;

import com.google.common.collect.ImmutableSet;
import io.leonis.algieba.*;
import io.leonis.algieba.geometry.Orientation;
import io.leonis.algieba.statistic.*;
import io.leonis.zosma.Identifiable;
import java.io.Serializable;
import java.util.Set;
import lombok.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

/**
 * The Interface Player.
 *
 * This interface represents a robot in a Small Size League game.
 *
 * @author Rimon Oz
 */
public interface Player extends Spatial, Identifiable, Orientation, Temporal, Serializable {

  /**
   * @return The X-position coordinate of the {@link Player} in mm.
   */
  default double getX() {
    return this.getState().getMean().getDouble(1, 0);
  }

  /**
   * @return The {@link Distribution state distribution} of the {@link Player}.
   */
  Distribution getState();

  /**
   * @return The Y-position coordinate of the {@link Player} in mm.
   */
  default double getY() {
    return this.getState().getMean().getDouble(2, 0);
  }

  /**
   * @return The orientation of the {@link Player} in radians.
   */
  @Override
  default double getOrientation() {
    return this.getState().getMean().getDouble(3, 0);
  }

  /**
   * @return The XY-position of the {@link Player} in (mm, mm).
   */
  default INDArray getXY() {
    return this.getState().getMean().get(NDArrayIndex.interval(1, 3), NDArrayIndex.all());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  default INDArray getPosition() {
    return this.getState().getMean().get(NDArrayIndex.interval(1, 4), NDArrayIndex.all());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  default long getTimestamp() {
    // fixed point conversion
    return Math.round(1000000L * this.getState().getMean().getDouble(0, 0));
  }

  /**
   * @return The identity for the {@link Player}, containing only its identifier and team color.
   */
  default PlayerIdentity getIdentity() {
    return new PlayerIdentity(this.getId(), this.getAllegiance());
  }

  /**
   * @return The number of the player {@link Player}.
   */
  int getId();

  /**
   * @return The {@link Allegiance} of the team to which this Player belongs.
   */
  Allegiance getAllegiance();

  @Value
  class Players implements SetSupplier<Player> {
    private final Set<Player> ally, opponent;
  }

  /**
   * Represents the measured state of a {@link Player}.
   */
  @Value
  @AllArgsConstructor
  class State implements Player {
    private final int id;
    private final Distribution state;
    private final Allegiance allegiance;

    public State(
        final int id,
        final double timestamp,
        final double x,
        final double y,
        final double orientation,
        final Allegiance allegiance
    ) {
      this(
          id,
          new SimpleDistribution(Nd4j.create(
              new double[]{
                  timestamp,
                  x,
                  y,
                  orientation
              },
              new int[]{4, 1}), Nd4j.eye(4)),
          allegiance);
    }
  }

  /**
   * The identity of the robot.
   */
  @Value
  class PlayerIdentity {
    private final int id;
    private final Allegiance allegiance;
  }
}
