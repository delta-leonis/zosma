package io.leonis.zosma.ipc.protobuf.refbox;

import static io.leonis.zosma.game.data.TeamColor.BLUE;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * The Class TeamAdapter.
 *
 * Adapts a {@link SSL_Referee} into a {@link Team}.
 *
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class TeamAdapter implements BiFunction<SSL_Referee, TeamColor, Team> {
  private final Allegiance allegiance;

  @Override
  public Team apply(final SSL_Referee referee, final TeamColor teamColor) {
    TeamInfo teamInfo = teamColor.equals(BLUE) ? referee.getBlue() : referee.getYellow();
    return new Team.State(
      referee.getPacketTimestamp(),
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
