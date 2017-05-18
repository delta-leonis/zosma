package org.ssh.ipc.serialization.protobuf;

import com.google.protobuf.*;
import org.ssh.ipc.serialization.Reader;

/**
 * The Interface ProtobufReader
 *
 * Interface used to couple a {@link Parser} and {@link Reader}.
 *
 * @param <I> Input type.
 * @param <O> Output type.
 * @author Jeroen de Jong
 */
public interface ProtobufReader<I extends GeneratedMessage, O> extends Reader<I> {
  /**
   * Read the binary protobuf data.
   *
   * @param input The protobuf data.
   * @return Instance of type {@code <O>}.
   */
  default O read(final byte[] input) throws InvalidProtocolBufferException {
    return this.read(getParser().parseFrom(input));
  }

  /**
   * @return Protobuf parser for {@code <I>}
   */
  Parser<I> getParser();
}
