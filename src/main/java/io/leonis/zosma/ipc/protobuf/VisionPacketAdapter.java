package io.leonis.zosma.ipc.protobuf;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.protobuf.VisionPacketAdapter.VisionPacket;
import io.leonis.zosma.ipc.protobuf.vision.*;
import io.reactivex.functions.*;
import java.util.Set;
import lombok.*;
import lombok.experimental.Delegate;
import org.robocup.ssl.Wrapper.WrapperPacket;

/**
 * The Class VisionPacketAdapter.
 *
 * This class represents a {@link Function} which creates {@link VisionPacket} from {@link
 * WrapperPacket}.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
@AllArgsConstructor
public final class VisionPacketAdapter implements
    BiFunction<WrapperPacket, AllegianceTuple<Team>, VisionPacket> {

  @Override
  public VisionPacket apply(
      final WrapperPacket wrapperPacket,
      final AllegianceTuple<Team> teams
  ) throws Exception {
    return new VisionPacket(
        new FieldAdapter().apply(wrapperPacket.getGeometry()),
        new BallsAdapter().apply(wrapperPacket.getDetection()),
        new PlayersAdapter().apply(wrapperPacket.getDetection(), teams));
  }

  @Value
  public static class VisionPacket implements Temporal {
    private final Field field;
    private final Set<Ball> balls;
    private final AllegianceTuple<Set<Player>> players;
    private final long timestamp = System.currentTimeMillis();
  }
}
