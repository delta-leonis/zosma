package io.leonis.zosma.math.filter;

import io.leonis.algieba.filter.KalmanFilter;
import io.leonis.algieba.statistic.SimpleDistribution;
import io.leonis.zosma.game.data.MovingPlayer;
import io.reactivex.functions.Function;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class MovingPlayersKalmanFilter.
 *
 * @author Rimon Oz
 */
@Value
public class MovingPlayersKalmanFilter
    implements Function<Set<MovingPlayer>, Set<MovingPlayer>>, BiFunction<Set<MovingPlayer>, Set<MovingPlayer>, Set<MovingPlayer>> {

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
  public Set<MovingPlayer> apply(final Set<MovingPlayer> players) {
    return apply(Collections.emptySet(), players);
  }

  @Override
  public Set<MovingPlayer> apply(
      final Set<MovingPlayer> previousResult,
      final Set<MovingPlayer> players
  ) {
    return players.stream()
        .map(foundPlayer ->
            previousResult.stream()
                .filter(previousPlayer ->
                    previousPlayer.getIdentity().equals(foundPlayer.getIdentity()))
                .findFirst()
                .<MovingPlayer>map(previousPlayer -> new MovingPlayer.State(
                    foundPlayer.getId(),
                    this.kalmanFilter.apply(
                        getStateTransitionMatrix(
                            (foundPlayer.getTimestamp() - previousPlayer.getTimestamp())
                                / 1000000d),
                        MEASUREMENT_TRANSITION_MATRIX,
                        CONTROL_TRANSITION_MATRIX,
                        Nd4j.zeros(7, 1),
                        PROCESS_COVARIANCE_MATRIX,
                        new SimpleDistribution(
                            foundPlayer.getState().getMean(),
                            MEASUREMENT_COVARIANCE_MATRIX),
                        previousPlayer.getState()),
                    foundPlayer.getTeamIdentity()))
                .orElse(foundPlayer))
        .collect(Collectors.toSet());
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
