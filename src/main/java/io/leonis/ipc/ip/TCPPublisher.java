package io.leonis.ipc.ip;

import io.netty.channel.ChannelOption;
import java.net.DatagramPacket;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.tcp.TcpServer;

/**
 * The Class TCPPublisher.
 *
 * This class represents a {@link TcpServer} which publishes {@link DatagramPacket}.
 *
 * @author Rimon Oz
 */
@Value
@Slf4j
public class TCPPublisher implements Publisher<DatagramPacket> {
  /**
   * The port to listen on.
   */
  private final int port;

  @Override
  public void subscribe(final Subscriber<? super DatagramPacket> subscriber) {
    TcpServer.create(opts -> opts.option(ChannelOption.SO_KEEPALIVE, true).listen(this.port))
        .newHandler((in, out) -> {
          in
              .receive().asByteArray()
              .map(bytes -> new DatagramPacket(bytes, bytes.length))
              .subscribe(subscriber);

          return Flux.never();
        })
        .subscribe();
  }
}
