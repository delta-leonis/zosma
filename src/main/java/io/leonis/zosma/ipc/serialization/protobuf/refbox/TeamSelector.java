package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import io.reactivex.functions.Function;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * @author jeroen.dejong.
 */
@AllArgsConstructor
public final class TeamSelector implements Function<SSL_Referee, Team> {
  private final Function<SSL_Referee, TeamInfo> getter;
  private final TeamIdentity teamIdentity;

  @Override
  public Team apply(final SSL_Referee ssl_referee) throws Exception {
    final TeamInfo teamInfo = getter.apply(ssl_referee);
    return new Team.State(
        ssl_referee.getPacketTimestamp(),
        teamInfo.getName(),
        teamInfo.getScore(),
        teamInfo.getRedCards(),
        teamInfo.getYellowCardTimesCount(),
        Collections.unmodifiableList(teamInfo.getYellowCardTimesList()),
        teamInfo.getTimeouts(),
        teamInfo.getTimeoutTime(),
        new PlayerIdentity(teamInfo.getGoalie(), teamIdentity),
        teamIdentity);
  }
}
