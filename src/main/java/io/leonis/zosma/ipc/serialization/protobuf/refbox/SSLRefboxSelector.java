package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.*;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import io.reactivex.functions.Function;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * The Class SSLRefboxSelector
 *
 * Reads {@link SSL_Referee} and formats this into a {@link Referee}.
 *
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class SSLRefboxSelector implements Function<SSL_Referee, Referee> {
  private final TeamIdentity allyTeam;
  private final Function<SSL_Referee, Team> blueTeamSelector = new TeamSelector(SSL_Referee::getBlue, TeamColor.BLUE);
  private final Function<SSL_Referee, Team> yellowTeamSelector = new TeamSelector(SSL_Referee::getYellow, TeamColor.YELLOW);

  @Override
  public Referee apply(final SSL_Referee packet) throws Exception {
    final Team blueTeam = blueTeamSelector.apply(packet);
    final Team yellowTeam = yellowTeamSelector.apply(packet);
    return new Referee.State(
      blueTeam.getIdentity().equals(allyTeam) ? blueTeam : yellowTeam,
      blueTeam.getIdentity().equals(allyTeam) ? yellowTeam : blueTeam,
      Stage.valueOf(packet.getStage().name()),
      packet.getStageTimeLeft(),
      Command.valueOf(packet.getCommand().name()),
      packet.getCommandTimestamp(),
      (packet.getBlueTeamOnPositiveHalf() ? blueTeam : yellowTeam).getIdentity(),
      packet.getCommandCounter(),
      packet.getPacketTimestamp());
  }
}
