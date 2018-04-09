package io.leonis.zosma.ipc.serialization.protobuf;

import io.leonis.subra.protocol.Robot.Command;
import io.leonis.zosma.game.data.Strategy;
import io.leonis.zosma.game.engine.Deducer;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class SSLCommandDeducer.
 *
 * Deduces a {@link io.leonis.zosma.game.data.Strategy} to individual {@link Command Commands}.
 *
 * @author Jeroen de Jong
 */
public class SSLCommandDeducer implements Deducer<Strategy.Supplier, Command> {

  @Override
  public Publisher<Command> apply(final Publisher<Strategy.Supplier> supplierPublisher) {
    return Flux.from(supplierPublisher)
        .flatMapIterable(strategyContainer -> strategyContainer.getStrategy().entrySet())
        .map(robotCommandEntry ->
            Command.newBuilder()
                .setRobotId(robotCommandEntry.getKey().getId())
                .setMove(Command.Move.newBuilder()
                    .setX(robotCommandEntry.getValue().getVelocityX())
                    .setY(robotCommandEntry.getValue().getVelocityY())
                    .setR(robotCommandEntry.getValue().getVelocityR())
                    .build())
                .setAction(Command.Action.newBuilder()
                    .setKick(robotCommandEntry.getValue().getFlatKick())
                    .setChip(robotCommandEntry.getValue().getChipKick())
                    .setDribble(robotCommandEntry.getValue().getDribblerSpin())
                    .build())
                .build());
  }
}
