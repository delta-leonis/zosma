package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.TeamColor.BLUE;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import java.util.*;
import java.util.stream.Collectors;
import org.robocup.ssl.Detection.*;

/**
 * The Class PlayerAdapter.
 *
 * Adapts a {@link DetectionFrame} into a {@link Set} of all {@link Player Players}.
 *
 * @author Jeroen de Jong
 */
public final class PlayerAdapter implements Function<DetectionFrame, Set<Player>> {
  private final Allegiance allegiance;
  private final Function<DetectionFrame, List<DetectionRobot>> getter;

  /**
   * @param allegiance allegiance of the targeted team.
   * @param teamColor  teamColor of the targeted team.
   */
  public PlayerAdapter(final Allegiance allegiance, final TeamColor teamColor) {
    this.allegiance = allegiance;
    this.getter = teamColor.equals(BLUE) ? DetectionFrame::getRobotsBlueList : DetectionFrame::getRobotsYellowList;
  }

  @Override
  public Set<Player> apply(final DetectionFrame detectionFrame) throws Exception {
    return getter.apply(detectionFrame).stream()
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
