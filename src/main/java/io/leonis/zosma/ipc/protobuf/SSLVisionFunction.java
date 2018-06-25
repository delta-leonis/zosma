package io.leonis.zosma.ipc.protobuf;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.protobuf.SSLVisionFunction.VisionPacket;
import io.leonis.zosma.ipc.protobuf.vision.*;
import io.reactivex.functions.*;
import java.util.Set;
import lombok.*;
import lombok.experimental.Delegate;
import org.robocup.ssl.Wrapper.WrapperPacket;

/**
 * The Class SSLVisionFunction.
 *
 * This class represents a {@link Function} which creates {@link VisionPacket} from {@link
 * WrapperPacket}.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
@AllArgsConstructor
public class SSLVisionFunction implements BiFunction<WrapperPacket, AllegianceTuple<Team>, VisionPacket> {

  @Override
  public VisionPacket apply(
      final WrapperPacket wrapperPacket,
      final AllegianceTuple<Team> teams
  ) throws Exception {
    return new VisionPacket(
        new GeometryFunction().apply(wrapperPacket.getGeometry()),
        new BallsSelector().apply(wrapperPacket.getDetection()),
        new PlayersSelector().apply(wrapperPacket.getDetection(), teams));
  }

  @Value @AllArgsConstructor
  public final static class VisionPacket implements Temporal {
    @Delegate
    private final Field field;
    private final Set<Ball> balls;
    private final AllegianceTuple<Set<Player>> players;
    private final long timestamp = System.currentTimeMillis();
  }
}
