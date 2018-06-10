package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Game.*;
import io.reactivex.functions.Function;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * The Class GameSelector
 *
 * Reads {@link SSL_Referee} and formats this into a {@link Game}.
 *
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class GameSelector implements Function<SSL_Referee, Game> {

  @Override
  public Game apply(final SSL_Referee packet) {
    return new Game.State(
      Stage.valueOf(packet.getStage().name()),
      packet.getStageTimeLeft(),
      Command.valueOf(packet.getCommand().name()),
      packet.getCommandTimestamp(),
      packet.getCommandCounter(),
      packet.getPacketTimestamp());
  }
}
