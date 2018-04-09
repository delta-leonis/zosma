package io.leonis.zosma.game.engine;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.*;
import java.util.Set;
import java.util.stream.Collectors;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * The Class BallsVelocityFunction.
 *
 * This class represents a {@link Function} which calculates velocities of {@link Ball}.
 *
 * @author Rimon Oz
 */
public class BallsVelocityFunction
    implements BiFunction<Set<Ball>, Set<MovingBall>, Set<MovingBall>> {

  @Override
  public Set<MovingBall> apply(final Set<Ball> balls, final Set<MovingBall> previousBalls) {
    return balls.stream()
        .map(currentBall ->
            previousBalls.stream()
                .reduce((closerBall, newBall) ->
                    Transforms.euclideanDistance(newBall.getXY(), currentBall.getXY())
                        > Transforms.euclideanDistance(
                        closerBall.getXY(), currentBall.getXY())
                        ? newBall
                        : closerBall)
                .map(previousBall -> new MovingBall.State(
                    currentBall.getTimestamp(),
                    currentBall.getX(),
                    currentBall.getY(),
                    currentBall.getZ(),
                    (currentBall.getX() - previousBall.getX())
                        / (currentBall.getTimestamp() - previousBall.getTimestamp()),
                    (currentBall.getY() - previousBall.getY())
                        / (currentBall.getTimestamp() - previousBall.getTimestamp()),
                    (currentBall.getZ() - previousBall.getZ())
                        / (currentBall.getTimestamp() - previousBall.getTimestamp())))
                .orElse(
                    new MovingBall.State(
                        currentBall.getTimestamp(),
                        currentBall.getX(),
                        currentBall.getY(),
                        currentBall.getZ())))
        .collect(Collectors.toSet());
  }
}
