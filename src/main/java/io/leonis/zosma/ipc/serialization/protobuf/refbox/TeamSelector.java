package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function3;
import java.util.Collections;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * @author jeroen.dejong.
 */
public final class TeamSelector implements Function3<TeamInfo, Allegiance, Long, Team> {

  @Override
  public Team apply(final TeamInfo teamInfo, final Allegiance allegiance, final Long timestamp) {
    return new Team.State(
      timestamp,
      teamInfo.getName(),
      teamInfo.getGoalie(),
      allegiance,
      new TeamData.State(
        teamInfo.getScore(),
        teamInfo.getRedCards(),
        teamInfo.getYellowCardTimesCount(),
        Collections.unmodifiableList(teamInfo.getYellowCardTimesList()),
        teamInfo.getTimeouts(),
        teamInfo.getTimeoutTime(),
        timestamp));
  }
}
