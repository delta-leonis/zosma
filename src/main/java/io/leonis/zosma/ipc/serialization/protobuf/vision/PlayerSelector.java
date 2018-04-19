package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Detection.*;

/**
 * @author jeroen.dejong.
 */
@AllArgsConstructor
public class PlayerSelector implements BiFunction<DetectionFrame, Allegiance, Set<Player>> {
  private final Function<DetectionFrame, List<DetectionRobot>> selector;

  @Override
  public Set<Player> apply(final DetectionFrame detectionFrame, final Allegiance allegiance)
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
