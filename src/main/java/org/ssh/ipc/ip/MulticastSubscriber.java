package org.ssh.ipc.ip;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * The Class MulticastSubscriber.
 *
 * This class describes a {@link Subscriber} of {@link DatagramPacket} which
 * publishes these packets on multicast.
 *
 * @author Jeroen de Jong
 */
@Value
@Slf4j
public class MulticastSubscriber implements Subscriber<DatagramPacket> {
    /** The {@link MulticastSocket socket}. */
    private final MulticastSocket socket;
    /** The port to which packets are sent. */
    private final int port;
    /** The address to which packets are sent. */
    private final InetAddress address;

    /**
     * Constructs a new MulticastSubscriber.
     * @param address The {@link InetAddress address} on which the subscriber needs to listen.
     * @param port    The port on which the subscriber needs to listen.
     */
    public MulticastSubscriber(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        try {
            this.socket = new MulticastSocket(port);
            this.socket.joinGroup(address);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(DatagramPacket datagramPacket) {
        try {
            datagramPacket.setAddress(this.getAddress());
            datagramPacket.setPort(this.getPort());
            this.getSocket().send(datagramPacket);
        } catch (IOException exception) {
            log.warn("Could not send packet to multicast "
                    + this.getAddress().getHostAddress()
                    + ":" + this.getPort());
            this.onError(exception);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Encountered an error! " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.trace("No more data to transmit! Completed!");
    }
}
