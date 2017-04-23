package org.ssh.ipc.ip;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class MulticastPublisher.
 *
 * This class describes a {@link Publisher} of {@link DatagramPacket} received
 * from multicast.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Slf4j
@Value
public class MulticastPublisher implements Publisher<DatagramPacket> {

  /**
   * The size of the buffer.
   */
  public static final int BUFFER_SIZE = 1024;
  /**
   * The {@link MulticastSocket socket}.
   */
  private final MulticastSocket socket;

  /**
   * Constructs a new MulticastPublisher.
   *
   * @param address The address to which messages are published as an {@link InetAddress}.
   * @param port    The port on which the messages are published.
   * @throws IOException If port cannot be opened
   */
  public MulticastPublisher(InetAddress address, int port) throws IOException {
    this.socket = new MulticastSocket(null);
    this.socket.setReuseAddress(true);
    this.socket.bind(new InetSocketAddress(address, port));
    this.socket.joinGroup(address);
  }

  @Override
  public void subscribe(Subscriber<? super DatagramPacket> subscriber) {
    Flux.<DatagramPacket>create(emitter -> {
      while (!socket.isClosed()) {
        try {
          DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
          socket.receive(packet);
        } catch (IOException exception) {
          emitter.error(exception);
        }
      }
      emitter.complete();
    })
        .subscribeOn(Schedulers.single())
        .subscribe(subscriber);
  }
}
