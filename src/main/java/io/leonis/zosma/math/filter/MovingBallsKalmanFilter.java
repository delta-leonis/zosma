package io.leonis.zosma.math.filter;

import io.leonis.algieba.filter.KalmanFilter;
import io.leonis.algieba.statistic.SimpleDistribution;
import io.leonis.zosma.game.data.MovingBall;
import io.reactivex.functions.*;
import java.util.Set;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class MovingBallsKalmanFilter.
 *
 * @author Rimon Oz
 */
public class MovingBallsKalmanFilter
    implements BiFunction<MovingBall, Set<MovingBall>, MovingBall>, Function<Set<MovingBall>, MovingBall>  {

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
  public MovingBall apply(final Set<MovingBall> balls) {
    return apply(new MovingBall.State(0, 0, 0, 0, 0, 0, 0), balls);
  }

  // TODO Impl might be broken. previousResult is not used, the other ballstates are used instead.
  @Override
  public MovingBall apply(final MovingBall previousResult, final Set<MovingBall> balls) {
    return balls.stream()
        .reduce((previousBall, foundBall) ->
            new MovingBall.State(
                this.kalmanFilter.apply(
                    getStateTransitionMatrix(
                        (foundBall.getTimestamp() - previousBall.getTimestamp())
                            / 1000000d),
                    MEASUREMENT_TRANSITION_MATRIX,
                    CONTROL_TRANSITION_MATRIX,
                    Nd4j.zeros(7, 1),
                    PROCESS_COVARIANCE_MATRIX,
                    new SimpleDistribution(
                        previousBall.getState().getMean(),
                        MEASUREMENT_COVARIANCE_MATRIX),
                    foundBall.getState())))
        .orElse(new MovingBall.State(0, 0, 0, 0, 0, 0, 0));
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
