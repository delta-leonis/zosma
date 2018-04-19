package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.Ball;
import io.reactivex.functions.Function;
import java.util.Set;
import java.util.stream.Collectors;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * @author jeroen.dejong.
 */
public class BallsSelector implements Function<DetectionFrame, Set<Ball>> {

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
