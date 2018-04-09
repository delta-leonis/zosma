package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import java.util.Set;
import java.util.stream.*;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * @author jeroen.dejong.
 */
public class PlayersSelector implements Function<DetectionFrame, Set<Player>> {

  @Override
  public Set<Player> apply(final DetectionFrame detectionFrame) throws Exception {
    return Stream.concat(
        new PlayerSelector(DetectionFrame::getRobotsBlueList, TeamColor.BLUE).apply(detectionFrame).stream(),
        new PlayerSelector(DetectionFrame::getRobotsYellowList, TeamColor.YELLOW).apply(detectionFrame).stream()
    ).collect(Collectors.toSet());
  }
}
