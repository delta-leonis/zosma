package io.leonis.zosma.ipc.serialization.protobuf;

import com.google.protobuf.Message;
import io.leonis.zosma.game.engine.Deducer;
import java.net.DatagramPacket;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The class DatagramDeducer.
 *
 * Deduces a protobuf {@link Message} to a {@link DatagramPacket}.
 *
 * @param <M> The type of {@link Message} to convert to a {@link DatagramPacket}.
 * @author Jeroen de Jong
 */
public class DatagramDeducer<M extends Message> implements Deducer<M, DatagramPacket> {

  @Override
  public Publisher<DatagramPacket> apply(final Publisher<M> messagePublisher) {
    return Flux.from(messagePublisher)
        .map(message -> new DatagramPacket(message.toByteArray(), message.getSerializedSize()));
  }
}
