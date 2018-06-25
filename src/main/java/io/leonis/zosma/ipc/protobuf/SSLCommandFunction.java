package io.leonis.zosma.ipc.protobuf;

import io.leonis.subra.protocol.Robot.Command;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.PlayerCommand;
import io.reactivex.functions.BiFunction;

/**
 * The Class SSLCommandFunction.
 *
 * Deduces a {@link io.leonis.zosma.game.data.Strategy} to individual {@link Command Commands}.
 *
 * @author Jeroen de Jong
 */
public class SSLCommandFunction implements BiFunction<PlayerIdentity, PlayerCommand, Command> {
  @Override
  public Command apply(final PlayerIdentity playerIdentity, final PlayerCommand playerCommand) {
    return Command.newBuilder()
        .setRobotId(playerIdentity.getId())
        .setMove(Command.Move.newBuilder()
            .setX(playerCommand.getVelocityX())
            .setY(playerCommand.getVelocityY())
            .setR(playerCommand.getVelocityR())
            .build())
        .setAction(Command.Action.newBuilder()
            .setKick(playerCommand.getFlatKick())
            .setChip(playerCommand.getChipKick())
            .setDribble(playerCommand.getDribblerSpin())
            .build())
        .build();
  }
}
