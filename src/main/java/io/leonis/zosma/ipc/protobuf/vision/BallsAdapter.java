package io.leonis.zosma.ipc.protobuf.vision;

import io.leonis.zosma.game.data.Ball;
import io.reactivex.functions.Function;
import java.util.Set;
import java.util.stream.Collectors;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * The Class BallsAdapter.
 *
 * Adapts a {@link DetectionFrame} into a {@link Set} of {@link Ball Balls}.
 * @author Jeroen de Jong
 */
public class BallsAdapter implements Function<DetectionFrame, Set<Ball>> {

  @Override
  public Set<Ball> apply(final DetectionFrame detectionFrame) {
    return detectionFrame.getBallsList().stream()
        .map(ball ->
            new Ball.State(
                detectionFrame.getTCapture(),
                ball.getX(),
                ball.getY(),
                ball.getZ()))
        .collect(Collectors.toSet());
  }
}
