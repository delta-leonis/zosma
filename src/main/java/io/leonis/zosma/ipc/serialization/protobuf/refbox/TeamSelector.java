package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function3;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * @author jeroen.dejong.
 */
@AllArgsConstructor
public final class TeamSelector implements Function3<TeamInfo, TeamColor, Long, Team> {
  private final Allegiance allegiance;

  @Override
  public Team apply(final TeamInfo teamInfo, final TeamColor teamColor, final Long timestamp) {
    return new Team.State(
      timestamp,
      teamInfo.getName(),
      teamColor,
      teamInfo.getGoalie(),
      this.allegiance,
      teamInfo.getScore(),
      teamInfo.getRedCards(),
      teamInfo.getYellowCardTimesCount(),
      Collections.unmodifiableList(teamInfo.getYellowCardTimesList()),
      teamInfo.getTimeouts(),
      teamInfo.getTimeoutTime());
  }
}
