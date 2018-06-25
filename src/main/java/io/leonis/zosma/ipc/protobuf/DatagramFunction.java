package io.leonis.zosma.ipc.protobuf;

import com.google.protobuf.Message;
import java.net.DatagramPacket;
import java.util.function.Function;

/**
 * The class DatagramFunction.
 *
 * Deduces a protobuf {@link Message} to a {@link DatagramPacket}.
 *
 * @param <M> The type of {@link Message} to convert to a {@link DatagramPacket}.
 * @author Jeroen de Jong
 */
public class DatagramFunction<M extends Message> implements Function<M, DatagramPacket> {

  @Override
  public DatagramPacket apply(final M msg) {
    return new DatagramPacket(msg.toByteArray(), msg.getSerializedSize());
  }
}
