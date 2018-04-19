package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import static io.leonis.zosma.game.data.Allegiance.*;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.*;
import io.leonis.zosma.game.data.Team.*;
import io.reactivex.functions.BiFunction;
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
public final class SSLRefboxSelector implements BiFunction<SSL_Referee, Allegiance, Referee> {
  private final TeamSelector<AllyTeam> allyTeamSelector = new TeamSelector<>(AllyTeam::new);
  private final TeamSelector<OpponentTeam> opponentTeamSelector = new TeamSelector<>(OpponentTeam::new);

  @Override
  public Referee apply(final SSL_Referee packet, final Allegiance blueTeamAllegiance) throws Exception {
    final AllyTeam ally = allyTeamSelector.apply(blueTeamAllegiance.equals(ALLY) ? packet.getBlue() : packet.getYellow(), packet.getPacketTimestamp());
    final OpponentTeam opponent = opponentTeamSelector.apply(blueTeamAllegiance.equals(OPPONENT) ? packet.getBlue() : packet.getYellow(), packet.getPacketTimestamp());
    return new Referee.State(
      ally,
      opponent,
      Stage.valueOf(packet.getStage().name()),
      packet.getStageTimeLeft(),
      Command.valueOf(packet.getCommand().name()),
      packet.getCommandTimestamp(),
      blueTeamAllegiance.equals(ALLY) && packet.getBlueTeamOnPositiveHalf() ? ALLY : OPPONENT,
      packet.getCommandCounter(),
      packet.getPacketTimestamp());
  }
}
