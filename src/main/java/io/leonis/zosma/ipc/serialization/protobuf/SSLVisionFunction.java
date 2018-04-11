package io.leonis.zosma.ipc.serialization.protobuf;

import io.leonis.algieba.Temporal;
import io.leonis.mx.Mx;
import io.leonis.zosma.game.data.Field;
import io.leonis.zosma.ipc.serialization.protobuf.SSLVisionFunction.VisionPacket;
import io.leonis.zosma.ipc.serialization.protobuf.vision.*;
import io.leonis.zosma.ipc.serialization.protobuf.vision.DetectionFrameFunction.DetectionFrame;
import io.reactivex.functions.Function;
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
public class SSLVisionFunction implements Function<WrapperPacket, VisionPacket> {

  @Override
  public VisionPacket apply(final WrapperPacket wrapperPacket) throws Exception {
    return Mx.mux(wrapperPacket)
        .join(Mx.first(WrapperPacket::getGeometry)
          .add(new GeometryFunction()))
        .join(Mx.first(WrapperPacket::getDetection)
          .add(new DetectionFrameFunction()))
        .demux(VisionPacket::new);
  }

  @AllArgsConstructor
  public final static class VisionPacket implements Temporal {
    @Delegate
    private final Field field;
    @Delegate
    private final DetectionFrame detectionContainer;
    @Getter
    private final long timestamp = System.currentTimeMillis();
  }
}
