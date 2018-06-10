package io.leonis.zosma.math.filter;

import io.leonis.algieba.filter.KalmanFilter;
import io.leonis.algieba.statistic.SimpleDistribution;
import io.leonis.zosma.game.data.MovingPlayer;
import java.util.function.BiFunction;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class MovingPlayerKalmanFilter.
 *
 * @author Rimon Oz
 */
@Value
public class MovingPlayerKalmanFilter
    implements BiFunction<MovingPlayer, MovingPlayer, MovingPlayer> {

  public static final INDArray MEASUREMENT_TRANSITION_MATRIX = Nd4j.create(
      new double[]{
          1, 0, 0, 0, 0, 0, 0,
          0, 1, 0, 0, 0, 0, 0,
          0, 0, 1, 0, 0, 0, 0,
          0, 0, 0, 1, 0, 0, 0,
          0, 0, 0, 0, 1, 0, 0,
          0, 0, 0, 0, 0, 1, 0,
          0, 0, 0, 0, 0, 0, 1
      },
      new int[]{7, 7});
  public static final INDArray MEASUREMENT_COVARIANCE_MATRIX = Nd4j.create(
      new double[]{
          0.01f, 0, 0, 0, 0, 0, 0,
          0, 0.01f, 0, 0, 0, 0, 0,
          0, 0, 0.01f, 0, 0, 0, 0,
          0, 0, 0, 0.01f, 0, 0, 0,
          0, 0, 0, 0, 0.01f, 0, 0,
          0, 0, 0, 0, 0, 0.01f, 0,
          0, 0, 0, 0, 0, 0, 0.01f
      },
      new int[]{7, 7});
  public static final INDArray CONTROL_TRANSITION_MATRIX = Nd4j.create(
      new double[]{
          1, 0, 0, 0, 0, 0, 0,
          0, 1, 0, 0, 0, 0, 0,
          0, 0, 1, 0, 0, 0, 0,
          0, 0, 0, 1, 0, 0, 0,
          0, 0, 0, 0, 1, 0, 0,
          0, 0, 0, 0, 0, 1, 0,
          0, 0, 0, 0, 0, 0, 1
      },
      new int[]{7, 7});
  public static final INDArray PROCESS_COVARIANCE_MATRIX = Nd4j.create(
      new double[]{
          0.01f, 0, 0, 0, 0, 0, 0,
          0, 0.01f, 0, 0, 0, 0, 0,
          0, 0, 0.01f, 0, 0, 0, 0,
          0, 0, 0, 0.01f, 0, 0, 0,
          0, 0, 0, 0, 0.01f, 0, 0,
          0, 0, 0, 0, 0, 0.01f, 0,
          0, 0, 0, 0, 0, 0, 0.01f
      },
      new int[]{7, 7});
  private final KalmanFilter kalmanFilter = new KalmanFilter();

  @Override
  public MovingPlayer apply(
      final MovingPlayer previousPlayer,
      final MovingPlayer player
  ) {
    return new MovingPlayer.State(
                    player.getId(),
                    this.kalmanFilter.apply(
                        getStateTransitionMatrix(
                            (player.getTimestamp() - previousPlayer.getTimestamp())
                                / 1000000d),
                        MEASUREMENT_TRANSITION_MATRIX,
                        CONTROL_TRANSITION_MATRIX,
                        Nd4j.zeros(7, 1),
                        PROCESS_COVARIANCE_MATRIX,
                        new SimpleDistribution(
                            player.getState().getMean(),
                            MEASUREMENT_COVARIANCE_MATRIX),
                        previousPlayer.getState()),
                    player.getAllegiance());
  }

  public static INDArray getStateTransitionMatrix(final double timeDifference) {
    return Nd4j.create(
        new double[]{
            1, 0, 0, 0, 0, 0, 0,
            0, 1, 0, 0, timeDifference, 0, 0,
            0, 0, 1, 0, 0, timeDifference, 0,
            0, 0, 0, 1, 0, 0, timeDifference,
            0, 0, 0, 0, 1, 0, 0,
            0, 0, 0, 0, 0, 1, 0,
            0, 0, 0, 0, 0, 0, 1
        },
        new int[]{7, 7});
  }
}
