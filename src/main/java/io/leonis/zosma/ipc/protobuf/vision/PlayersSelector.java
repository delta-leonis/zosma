package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.TeamColor.BLUE;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import java.util.Set;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * @author jeroen.dejong.
 */
public final class PlayersSelector implements
    BiFunction<DetectionFrame, AllegianceTuple<Team>, AllegianceTuple<Set<Player>>> {

  @Override
  public AllegianceTuple<Set<Player>> apply(
      final DetectionFrame detectionFrame,
      final AllegianceTuple<Team> teams
  ) throws Exception {
    return new AllegianceTuple<>(
        new PlayerSelector(ALLY,
            teams.getAlly().getTeamColor().equals(BLUE) ? DetectionFrame::getRobotsBlueList : DetectionFrame::getRobotsYellowList).apply(detectionFrame),
        new PlayerSelector(OPPONENT,
            teams.getOpponent().getTeamColor().equals(BLUE) ? DetectionFrame::getRobotsBlueList : DetectionFrame::getRobotsYellowList).apply(detectionFrame));
  }
}
