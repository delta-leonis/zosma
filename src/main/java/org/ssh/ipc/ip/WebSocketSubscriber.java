package org.ssh.ipc.ip;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.ssh.ipc.serialization.JsonSerializable;
import org.ssh.ipc.serialization.JsonSerializationContext;
import reactor.core.publisher.TopicProcessor;
import reactor.core.scheduler.Schedulers;
import reactor.ipc.netty.NettyPipeline;
import reactor.ipc.netty.http.server.HttpServer;


/**
 * The Class WebSocketSubscriber.
 *
 * This class subscribes on a stream of {@link Jsonable JSONifiable} data and publishes it to a
 * websocket
 *
 * @param <J> the type parameter
 * @author Rimon Oz
 */
@Slf4j
public class WebSocketSubscriber<J extends JsonSerializable> implements Subscriber<J> {

  /**
   * The processor which handles the passthru of JSON objects from the input to the websocket.
   */
  private final TopicProcessor<String> outputPublisher = TopicProcessor.create();

  /**
   * Creates a new subscriber which receives {@link Jsonable JSONifiable} data and emits it
   * on a websocket.
   *
   * @param uri  The URI of the websocket (eg. '/route').
   * @param port The port of the websocket (eg. 1337).
   */
  public WebSocketSubscriber(final String uri, final int port) {
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
  public void onNext(final J jsonable) {
    this.outputPublisher.onNext(jsonable.serialize(new JsonSerializationContext()));
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
