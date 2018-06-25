package io.leonis.zosma.ipc.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Detection.*;

/**
 * @author jeroen.dejong.
 */
@AllArgsConstructor
public class PlayerSelector implements Function<DetectionFrame, Set<Player>> {
  private final Allegiance allegiance;
  private final Function<DetectionFrame, List<DetectionRobot>> selector;

  @Override
  public Set<Player> apply(final DetectionFrame detectionFrame)
      throws Exception {
    return selector.apply(detectionFrame).stream()
        .map(robot -> new Player.State(
            robot.getRobotId(),
            detectionFrame.getTCapture(),
            robot.getX(),
            robot.getY(),
            robot.getOrientation(),
            allegiance))
        .collect(Collectors.toSet());
  }
}
