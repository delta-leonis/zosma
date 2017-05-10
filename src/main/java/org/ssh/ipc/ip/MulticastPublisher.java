package org.ssh.ipc.ip;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.InternetProtocolFamily;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.udp.UdpClient;

/**
 * The Class MulticastPublisher.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Slf4j
@Value
public class MulticastPublisher<O> implements Publisher<O> {
  /**
   * Address to listen to
   */
  private final InetAddress address;
  /**
   * Port to listen to
   */
  private final int port;
  /**
   * Function used to convert byte[] to target type
   */
  private final Function<byte[], O> parser;

  @Override
  public void subscribe(final Subscriber<? super O> subscriber) {
    try {
      final List<NetworkInterface> interfaces = Collections
          .list(NetworkInterface.getNetworkInterfaces());

      UdpClient
          .create(opts -> opts.option(ChannelOption.SO_REUSEADDR, true)
              .connect(this.port)
              .protocolFamily(InternetProtocolFamily.IPv4))
          .newHandler((in, out) -> {
            Flux.fromIterable(interfaces)
                .flatMap(iface -> in.join(this.address, iface))
                .thenMany(in.receive().asByteArray())
                .map(parser)
                .subscribe(subscriber);
            return Flux.never();
          })
          .subscribe();
    } catch (SocketException exception) {
      Flux.<O>error(exception).subscribe(subscriber);
    }
  }
}
