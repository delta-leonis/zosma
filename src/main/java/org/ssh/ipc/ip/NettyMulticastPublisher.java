package org.ssh.ipc.ip;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.InternetProtocolFamily;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
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
 */
@Slf4j
@Value
public class NettyMulticastPublisher implements Publisher<DatagramPacket> {
  private final InetAddress address;
  private final int port;

  @Override
  public void subscribe(Subscriber<? super DatagramPacket> subscriber) {
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

      UdpClient
          .create(opts -> opts.option(ChannelOption.SO_REUSEADDR, true)
              .connect(this.port)
              .protocolFamily(InternetProtocolFamily.IPv4))
          .newHandler((in, out) -> {
            Flux.fromIterable(interfaces)
                .flatMap(iface -> in.join(this.address, iface))
                .thenMany(in.receive().asByteArray())
                .map(bytes -> new DatagramPacket(bytes, bytes.length))
                .subscribe(subscriber);
            return Flux.never();
          })
          .subscribe();
    } catch (SocketException exception) {
      Flux.<DatagramPacket>error(exception).subscribe(subscriber);
    }
  }
}
