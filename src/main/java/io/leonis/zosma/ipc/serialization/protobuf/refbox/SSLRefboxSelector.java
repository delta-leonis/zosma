package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import com.google.common.collect.ImmutableSet;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.*;
import io.reactivex.functions.Function;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * The Class SSLRefboxSelector
 *
 * Reads {@link SSL_Referee} and formats this into a {@link Referee}.
 *
 * @author Jeroen de Jong
 */
public final class SSLRefboxSelector implements Function<SSL_Referee, Referee> {
  private final Function<SSL_Referee, Team> blueTeamSelector = new TeamSelector(SSL_Referee::getBlue, TeamColor.BLUE);
  private final Function<SSL_Referee, Team> yellowTeamSelector = new TeamSelector(SSL_Referee::getYellow, TeamColor.YELLOW);

  @Override
  public Referee apply(final SSL_Referee packet) throws Exception {
    return new Referee.State(
      packet.getPacketTimestamp(),
      Stage.valueOf(packet.getStage().name()),
      packet.getStageTimeLeft(),
      Command.valueOf(packet.getCommand().name()),
      ImmutableSet.of(blueTeamSelector.apply(packet), yellowTeamSelector.apply(packet)),
      packet.getCommandTimestamp(),
      packet.getBlueTeamOnPositiveHalf() ? TeamColor.BLUE : TeamColor.YELLOW,
      packet.getBlueTeamOnPositiveHalf() ? TeamColor.YELLOW : TeamColor.BLUE,
      packet.getCommandCounter());
  }
}
