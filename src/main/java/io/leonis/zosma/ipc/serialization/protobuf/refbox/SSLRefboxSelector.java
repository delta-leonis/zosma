package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import static io.leonis.zosma.game.data.Allegiance.*;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.*;
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

  @Override
  public Referee apply(final SSL_Referee packet, final Allegiance blueTeamAllegiance) {
    return new Referee.State(
      Stage.valueOf(packet.getStage().name()),
      packet.getStageTimeLeft(),
      Command.valueOf(packet.getCommand().name()),
      packet.getCommandTimestamp(),
      blueTeamAllegiance.equals(ALLY) && packet.getBlueTeamOnPositiveHalf() ? ALLY : OPPONENT,
      packet.getCommandCounter(),
      packet.getPacketTimestamp());
  }
}
