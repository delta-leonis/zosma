package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import java.util.Set;
import org.robocup.ssl.Detection.DetectionFrame;

/**
 * The Class PlayersAdapter.
 *
 * Adapts a {@link DetectionFrame} to a {@link AllegianceTuple} of all {@link Player Players}. The
 * adapter uses the provided teams to determine the ally / opponent team.
 * @author jeroen.dejong.
 */
public final class PlayersAdapter implements
    BiFunction<DetectionFrame, AllegianceTuple<Team>, AllegianceTuple<Set<Player>>> {

  @Override
  public AllegianceTuple<Set<Player>> apply(
      final DetectionFrame detectionFrame,
      final AllegianceTuple<Team> teams
  ) throws Exception {
    return new AllegianceTuple<>(
      new PlayerAdapter(ALLY, teams.getAlly().getTeamColor()).apply(detectionFrame),
      new PlayerAdapter(OPPONENT, teams.getOpponent().getTeamColor()).apply(detectionFrame));
  }
}
