package org.ssh.ipc.ip;

import java.io.Serializable;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.*;
import reactor.core.publisher.TopicProcessor;
import reactor.core.scheduler.Schedulers;
import reactor.ipc.netty.NettyPipeline;
import reactor.ipc.netty.http.server.HttpServer;


/**
 * The Class WebSocketSubscriber.
 *
 * This class subscribes on a stream of {@link Serializable} data and publishes it to a
 * websocket
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Slf4j
public class WebSocketSubscriber<T> implements Subscriber<T> {
  /**
   * The processor which handles the passthru of JSON objects from the input to the websocket.
   */
  private final TopicProcessor<String> outputPublisher = TopicProcessor.create();
  private final Function<T, String> writer;

  /**
   * Creates a new subscriber which receives {@link Serializable} data and emits it
   * on a websocket.
   *
   * @param writer The writer for serializing received Serializables
   * @param uri    The URI  of the websocket (eg. '/route').
   * @param port   The port of the websocket (eg. 1337).
   */
  public WebSocketSubscriber(
      final Function<T, String> writer,
      final String uri,
      final int port) {
    this.writer = writer;
    HttpServer.create(port)
        .newHandler((in, out) ->
            in.uri().equals(uri)
                ? out.sendWebsocket((inboundWebsocket, outboundWebsocket) ->
                outboundWebsocket
                    .options(NettyPipeline.SendOptions::flushOnEach)
                    .sendString(this.outputPublisher))
                : out.sendNotFound())
        .subscribeOn(Schedulers.single())
        .subscribe();
  }

  @Override
  public void onSubscribe(final Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final T object) {
    this.outputPublisher.onNext(writer.apply(object));
  }

  @Override
  public void onError(final Throwable t) {
    log.error(t.toString());
  }

  @Override
  public void onComplete() {
    this.outputPublisher.onComplete();
  }
}
