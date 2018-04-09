package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.serialization.protobuf.vision.DetectionFrameFunction.DetectionFrame;
import io.reactivex.functions.Function;
import java.util.Set;
import java.util.stream.*;
import lombok.Value;
import org.robocup.ssl.Detection;
import org.robocup.ssl.Detection.DetectionRobot;

/**
 * The Class DetectionFrameFunction.
 *
 * This class represents a {@link Function} of {@link Detection.DetectionFrame} to
 * {@link DetectionFrame}.
 *
 * @author Rimon Oz
 */
public class DetectionFrameFunction implements Function<Detection.DetectionFrame, DetectionFrame> {
  @Override
  public DetectionFrame apply(
      final Detection.DetectionFrame detectionFrame
  ) {
    return new DetectionFrame(
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
                    .collect(Collectors.toSet()));
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
  public static class DetectionFrame {
    private final Set<Ball> balls;
    private final Set<Player> players;
  }
}
