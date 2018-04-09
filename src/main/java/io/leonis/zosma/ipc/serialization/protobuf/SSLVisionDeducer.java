package io.leonis.zosma.ipc.serialization.protobuf;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.engine.*;
import io.leonis.zosma.ipc.serialization.protobuf.SSLVisionDeducer.VisionPacket;
import io.leonis.zosma.ipc.serialization.protobuf.vision.*;
import io.leonis.zosma.ipc.serialization.protobuf.vision.DetectionFrameDeducer.DetectionFrame;
import io.leonis.zosma.ipc.serialization.protobuf.vision.GeometryDeducer.GeometryFrame;
import lombok.*;
import lombok.experimental.Delegate;
import org.reactivestreams.Publisher;
import org.robocup.ssl.Wrapper.WrapperPacket;
import reactor.core.publisher.Flux;

/**
 * The Class SSLVisionDeducer.
 *
 * This class represents a {@link Deducer} which creates {@link VisionPacket} from {@link
 * WrapperPacket}.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
@AllArgsConstructor
public class SSLVisionDeducer implements Deducer<WrapperPacket, VisionPacket> {

  @Override
  public Publisher<VisionPacket> apply(final Publisher<WrapperPacket> wrapperPacketPublisher) {
    return Flux.from(wrapperPacketPublisher)
        .transform(
            new ParallelDeducer<>(
                input -> Flux.from(input)
                    .filter(WrapperPacket::hasGeometry)
                    .map(WrapperPacket::getGeometry)
                    .transform(new GeometryDeducer()),
                input -> Flux.from(input)
                    .filter(WrapperPacket::hasDetection)
                    .map(WrapperPacket::getDetection)
                    .transform(new DetectionFrameDeducer()),
                VisionPacket::new));
  }

  @AllArgsConstructor
  public final static class VisionPacket implements Player.SetSupplier, GoalDimension.Supplier, Field.Supplier, Ball.SetSupplier, Temporal {
    @Delegate
    private final GeometryFrame geometryContainer;
    @Delegate
    private final DetectionFrame detectionContainer;
    @Getter
    private final long timestamp = System.currentTimeMillis();
  }
}
