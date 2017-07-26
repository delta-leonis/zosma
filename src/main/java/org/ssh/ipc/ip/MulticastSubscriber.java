package org.ssh.ipc.ip;

import java.io.IOException;
import java.net.*;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.*;

/**
 * The Class MulticastSubscriber.
 *
 * This class describes a {@link Subscriber} of {@link DatagramPacket} which
 * publishes these packets on multicast.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
@Slf4j
public class MulticastSubscriber implements Subscriber<DatagramPacket> {

  /**
   * The {@link MulticastSocket socket}.
   */
  private final MulticastSocket socket;
  /**
   * The port to which packets are sent.
   */
  private final int port;
  /**
   * The address to which packets are sent.
   */
  private final InetAddress address;

  /**
   * Constructs a new MulticastSubscriber.
   *
   * @param address The {@link InetAddress address} on which the subscriber needs to listen.
   * @param port    The port on which the subscriber needs to listen.
   * @throws IOException if socket cannot be opened
   */
  public MulticastSubscriber(final InetAddress address, final int port) throws IOException {
    this.address = address;
    this.port = port;
    this.socket = new MulticastSocket(port);
    this.socket.joinGroup(address);
  }

  @Override
  public void onSubscribe(final Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final DatagramPacket datagramPacket) {
    try {
      datagramPacket.setAddress(this.getAddress());
      datagramPacket.setPort(this.getPort());
      this.getSocket().send(datagramPacket);
    } catch (final Exception exception) {
      log.error("Could not send packet to multicast "
          + this.getAddress().getHostAddress()
          + ":" + this.getPort());
      this.onError(exception);
    }
  }

  @Override
  public void onError(final Throwable throwable) {
    log.error("Encountered an error! " + throwable.getMessage());
  }

  @Override
  public void onComplete() {
    log.info("No more data to transmit! Completed!");
  }
}
