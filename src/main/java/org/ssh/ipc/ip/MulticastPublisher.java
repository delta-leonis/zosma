package org.ssh.ipc.ip;

import lombok.Value;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * The Class MulticastPublisher.
 *
 * This class describes a {@link Publisher} of {@link DatagramPacket} received
 * from multicast.
 *
 * @author Jeroen de Jong
 */
@Value
public class MulticastPublisher implements Publisher<DatagramPacket> {
    /** The {@link MulticastSocket socket}. */
    private final MulticastSocket socket;
    /** The size of the buffer. */
    public static int             BUFFER_SIZE = 1024;

    /**
     * Constructs a new MulticastPublisher.
     * @param address The address to which messages are published as an {@link InetAddress}.
     * @param port    The port on which the messages are published.
     */
    public MulticastPublisher(InetAddress address, int port) {
        try {
            this.socket = new MulticastSocket(port);
            this.socket.joinGroup(address);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void subscribe(Subscriber<? super DatagramPacket> subscriber) {
        Flux.<DatagramPacket>from(emitter -> {
            try {
                while (socket.isConnected()) {
                    DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
                    socket.receive(packet);
                    emitter.onNext(packet);
                }
            } catch(IOException exception){
                emitter.onError(exception);
            } finally {
                emitter.onComplete();
            }
        }).subscribe(subscriber);
    }
}
