package io.leonis.zosma.ipc.network;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.engine.*;
import io.leonis.zosma.ipc.network.GamePublisher.GameFrame;
import io.leonis.zosma.ipc.serialization.protobuf.*;
import io.leonis.zosma.ipc.serialization.protobuf.SSLVisionDeducer.VisionPacket;
import io.leonis.zosma.ipc.serialization.protobuf.vision.GoalsDeducer;
import java.util.Set;
import lombok.*;
import lombok.experimental.Delegate;
import org.reactivestreams.*;
import org.robocup.ssl.Referee.SSL_Referee;
import org.robocup.ssl.Wrapper.WrapperPacket;
import reactor.core.publisher.Flux;

/**
 * The Class GamePublisher.
 *
 * This class contains the functionality of a {@link Publisher} of {@link GameFrame}.
 *
 * @author Rimon Oz
 */
@AllArgsConstructor
public final class GamePublisher implements Publisher<GameFrame> {
  private final Publisher<WrapperPacket> visionPublisher;
  private final Publisher<SSL_Referee> refboxPublisher;

  @Override
  public void subscribe(final Subscriber<? super GameFrame> subscriber) {
    Flux.combineLatest(
        Flux.from(this.visionPublisher).transform(new SSLVisionDeducer()),
        Flux.from(this.refboxPublisher).transform(new SSLRefboxDeducer()),
        GameFrameWithoutGoals::new)
      .transform(new ParallelDeducer<>(
          new IdentityDeducer<>(),
          new GoalsDeducer<>(),
          GameFrame::new))
      .subscribe(subscriber);
  }

  @Value
  private static class GameFrameWithoutGoals
      implements Player.SetSupplier, GoalDimension.Supplier, Field.Supplier, Ball.SetSupplier,
      Referee.Supplier {
    private final Set<Player> players;
    private final GoalDimension goalDimension;
    private final Set<Ball> balls;
    private final Field field;
    private final Referee referee;

    GameFrameWithoutGoals(final VisionPacket packet, final Referee.Supplier referee) {
      this.goalDimension = packet.getGoalDimension();
      this.players = packet.getPlayers();
      this.balls = packet.getBalls();
      this.field = packet.getField();
      this.referee = referee.getReferee();
    }
  }

  @Value
  public static class GameFrame
      implements Player.SetSupplier, Goal.SetSupplier, Field.Supplier, Ball.SetSupplier,
      Referee.Supplier, Temporal {
    @Delegate
    private final GameFrameWithoutGoals state;
    @Delegate
    private final Goal.SetSupplier goalSupplier;
    private final long timestamp = System.currentTimeMillis();
  }
}
