package io.leonis.zosma.game.data;

import io.leonis.algieba.*;
import io.leonis.algieba.statistic.*;
import java.io.Serializable;
import lombok.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

/**
 * The Interface Ball.
 *
 * This interface describes the functionality of a ball.
 *
 * @author Rimon Oz
 */
public interface Ball extends Spatial, Temporal, Serializable {

  @Override
  default long getTimestamp() {
    // fixed point conversion
    return Math.round(1000000L * this.getState().getMean().getDouble(0, 0));
  }

  /**
   * @return The {@link Distribution state distribution} of the ball.
   */
  Distribution getState();

  /**
   * @return The X-position coordinate of the {@link Ball} in mm.
   */
  default double getX() {
    return this.getState().getMean().getDouble(1, 0);
  }

  /**
   * @return The Y-position coordinate of the {@link Ball} in mm.
   */
  default double getY() {
    return this.getState().getMean().getDouble(2, 0);
  }

  /**
   * @return The Z-position coordinate of the {@link Ball} in mm.
   */
  default double getZ() {
    return this.getState().getMean().getDouble(3, 0);
  }

  /**
   * @return The XY-position of the {@link Ball} in (mm, mm).
   */
  default INDArray getXY() {
    return this.getState().getMean().get(NDArrayIndex.interval(1, 3), NDArrayIndex.all());
  }

  @Override
  default INDArray getPosition() {
    return this.getState().getMean().get(NDArrayIndex.interval(1, 4), NDArrayIndex.all());
  }

  @Value
  @AllArgsConstructor
  class State implements Ball {
    private final Distribution state;

    public State(
        final double timestamp,
        final double x,
        final double y,
        final double z
    ) {
      this(new SimpleDistribution(Nd4j.create(
          new double[]{
              timestamp,
              x,
              y,
              z
          },
          new int[]{4, 1}), Nd4j.eye(4)));
    }
  }
}
