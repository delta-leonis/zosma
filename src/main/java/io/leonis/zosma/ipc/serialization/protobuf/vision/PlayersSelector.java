package io.leonis.zosma.ipc.serialization.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;

import io.leonis.zosma.game.data.Allegiance;
import io.leonis.zosma.game.data.Player.Players;
import io.reactivex.functions.BiFunction;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * @author jeroen.dejong.
 */
public class PlayersSelector implements BiFunction<DetectionFrame, Allegiance, Players> {
  private final PlayerSelector blueTeamSelector = new PlayerSelector(DetectionFrame::getRobotsBlueList);
  private final PlayerSelector yellowTeamSelector = new PlayerSelector(DetectionFrame::getRobotsYellowList);

  @Override
  public Players apply(final DetectionFrame detectionFrame, final Allegiance blueTeamAllegiance)
      throws Exception {
    return new Players(
        blueTeamAllegiance.equals(ALLY) ? blueTeamSelector.apply(detectionFrame, ALLY) : yellowTeamSelector.apply(detectionFrame, ALLY),
        blueTeamAllegiance.equals(OPPONENT) ? blueTeamSelector.apply(detectionFrame, OPPONENT) : yellowTeamSelector.apply(detectionFrame, OPPONENT));
  }
}
