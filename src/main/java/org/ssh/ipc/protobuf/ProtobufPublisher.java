package org.ssh.ipc.protobuf;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Parser;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.ssh.ipc.ip.MulticastPublisher;
import org.ssh.math.function.LambdaExceptions;
import reactor.core.publisher.Flux;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * The Class ProtobufPublisher.
 *
 * This class describes a {@link Publisher} of {@link GeneratedMessage protobuf packets}
 * which receives protobuf packets from multicast and publishes them.
 *
 * @author Jeroen de Jong
 */
@Slf4j
@Value
public class ProtobufPublisher<M extends GeneratedMessage> implements Publisher<M> {
    /**
     * The {@link com.google.protobuf.Parser protobuf parser}.
     */
    private final Parser<M> parser;
    /**
     * The {@link InetAddress} to which messages are published.
     */
    private final InetAddress address;
    /**
     * The port to which messages are published.
     */
    private final int port;

    @Override
    public void subscribe(Subscriber<? super M> subscriber) {
        Flux.from(new MulticastPublisher(this.getAddress(), this.getPort()))
                .map(DatagramPacket::getData)
                .map(LambdaExceptions.rethrowFunction(getParser()::parseFrom))
                .subscribe(subscriber);
    }
}
