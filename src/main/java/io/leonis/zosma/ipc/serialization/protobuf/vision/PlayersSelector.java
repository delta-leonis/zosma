package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import java.util.Set;
import java.util.stream.*;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * @author jeroen.dejong.
 */
public class PlayersSelector implements BiFunction<DetectionFrame, Allegiance, Set<Player>> {
  private final PlayerSelector blueTeamSelector = new PlayerSelector(DetectionFrame::getRobotsBlueList);
  private final PlayerSelector yellowTeamSelector = new PlayerSelector(DetectionFrame::getRobotsYellowList);

  @Override
  public Set<Player> apply(final DetectionFrame detectionFrame, final Allegiance blueTeamAllegiance)
      throws Exception {
    return Stream.concat(
        blueTeamSelector.apply(detectionFrame, blueTeamAllegiance).stream(),
        yellowTeamSelector.apply(detectionFrame, blueTeamAllegiance.opponent()).stream()
    ).collect(Collectors.toSet());
  }
}
