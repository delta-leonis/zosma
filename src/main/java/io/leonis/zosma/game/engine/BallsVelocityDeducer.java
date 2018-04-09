package io.leonis.zosma.game.engine;

import io.leonis.zosma.game.data.*;
import java.util.*;
import java.util.stream.Collectors;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class BallsVelocityDeducer.
 *
 * This class represents a {@link Deducer} which calculates velocities of {@link Ball}.
 *
 * @param <I> The type of state carrying a {@link Set} of {@link Ball}.
 * @author Rimon Oz
 */
public class BallsVelocityDeducer<I extends Ball.SetSupplier>
    implements Deducer<I, Set<MovingBall>> {
  @Override
  public Publisher<Set<MovingBall>> apply(final Publisher<I> iPublisher) {
    return Flux.from(iPublisher)
        .scan(Collections.emptySet(), (previousGame, currentGame) ->
            currentGame.getBalls().stream()
                .map(currentBall ->
                    previousGame.stream()
                        .reduce((closerBall, newBall) ->
                            Transforms.euclideanDistance(newBall.getXY(), currentBall.getXY())
                                > Transforms.euclideanDistance(
                                closerBall.getXY(), currentBall.getXY())
                                ? newBall
                                : closerBall)
                        .map(closestBall -> new MovingBall.State(currentBall, closestBall))
                        .orElse(
                            new MovingBall.State(
                                currentBall.getTimestamp(),
                                currentBall.getX(),
                                currentBall.getY(),
                                currentBall.getZ(),
                                0d,
                                0d,
                                0d)))
                .collect(Collectors.toSet()));
  }
}
