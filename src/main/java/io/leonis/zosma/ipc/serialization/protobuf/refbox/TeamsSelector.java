package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import static io.leonis.zosma.game.data.Allegiance.*;

import io.leonis.zosma.game.data.Allegiance;
import io.leonis.zosma.game.data.Team.Teams;
import io.reactivex.functions.BiFunction;
import org.robocup.ssl.Referee.SSL_Referee;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * @author jeroen.dejong.
 */
public final class TeamsSelector implements BiFunction<SSL_Referee, Allegiance, Teams> {
  private final TeamSelector teamSelector = new TeamSelector();

  @Override
  public Teams apply(final SSL_Referee ssl_referee, final Allegiance blueTeamAllegiance) {
    TeamInfo allyTeam = blueTeamAllegiance.equals(ALLY) ? ssl_referee.getBlue() : ssl_referee.getYellow();
    TeamInfo opponentTeam = blueTeamAllegiance.equals(OPPONENT) ? ssl_referee.getBlue() : ssl_referee.getYellow();
    return new Teams(
      teamSelector.apply(allyTeam, ALLY, ssl_referee.getPacketTimestamp()),
      teamSelector.apply(opponentTeam, OPPONENT, ssl_referee.getPacketTimestamp()));
  }
}
