package io.leonis.zosma.ipc.ip;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.InternetProtocolFamily;
import java.net.DatagramPacket;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.udp.UdpClient;

/**
 * The Class UDPPublisher.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Slf4j
@Value
public class UDPPublisher implements Publisher<DatagramPacket> {
  /**
   * Port to listen on.
   */
  private final int port;

  @Override
  public void subscribe(final Subscriber<? super DatagramPacket> subscriber) {
    UdpClient
        .create(opts -> opts
            .option(ChannelOption.SO_REUSEADDR, true)
            .connect(this.port)
            .protocolFamily(InternetProtocolFamily.IPv4))
        .newHandler((in, out) -> {
          in.receive().asByteArray()
              .map(bytes -> new DatagramPacket(bytes, bytes.length))
              .subscribe(subscriber);
          return Flux.never();
        })
        .subscribe();
  }
}
