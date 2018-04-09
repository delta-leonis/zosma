package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.engine.Deducer;
import io.leonis.zosma.ipc.serialization.protobuf.vision.DetectionFrameDeducer.DetectionFrame;
import java.util.Set;
import java.util.stream.*;
import lombok.Value;
import org.reactivestreams.Publisher;
import org.robocup.ssl.Detection;
import org.robocup.ssl.Detection.DetectionRobot;
import reactor.core.publisher.Flux;

/**
 * The Class DetectionFrameDeducer.
 *
 * This class represents a {@link Deducer} of {@link Detection.DetectionFrame} to {@link DetectionFrame}.
 *
 * @author Rimon Oz
 */
public class DetectionFrameDeducer implements Deducer<Detection.DetectionFrame, DetectionFrame> {
  @Override
  public Publisher<DetectionFrame> apply(
      final Publisher<Detection.DetectionFrame> detectionFramePublisher
  ) {
    return Flux.from(detectionFramePublisher)
        .map(detectionFrame ->
            new DetectionFrame(
                detectionFrame.getBallsList().stream()
                    .map(ball ->
                        new Ball.State(
                            detectionFrame.getTCapture(),
                            ball.getX(),
                            ball.getY(),
                            ball.getZ()))
                    .collect(Collectors.toSet()),
                Stream.of(detectionFrame)
                    .flatMap(input -> Stream.concat(
                        input.getRobotsYellowList().stream()
                            .map(robot ->
                                this.createPlayer(TeamColor.YELLOW, input.getTCapture(), robot)),
                        input.getRobotsBlueList().stream()
                            .map(robot ->
                                this.createPlayer(TeamColor.BLUE, input.getTCapture(), robot))))
                    .collect(Collectors.toSet())));
  }

  private Player createPlayer(
      final TeamColor teamColor,
      final Double timestamp,
      final DetectionRobot robotData
  ) {
    return new Player.State(
        robotData.getRobotId(),
        timestamp,
        robotData.getX(),
        robotData.getY(),
        robotData.getOrientation(),
        teamColor);
  }

  @Value
  public static class DetectionFrame implements Ball.SetSupplier, Player.SetSupplier {
    private final Set<Ball> balls;
    private final Set<Player> players;
  }
}
