package org.ssh.game;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.*;

/**
 * The Interface BufferedStrategizer.
 * <p>
 * This interface describes the functionality of an object which
 * {@link Subscriber subscribes} to {@link Game a game}, creates a buffer and produces a
 * {@link Strategy} based on that buffer of {@link Game game states}.
 *
 * @param <S> The type of {@link Strategy} emitted by this strategizer.
 * @param <G> The type of {@link Game} for which this engine is meant.
 * @author Rimon Oz
 */
@Value
@NonFinal
public abstract class BufferedStrategizer<S extends Strategy, G extends Game>
    implements Strategizer<S, G> {
  /**
   * The size of the buffer.
   */
  private final int bufferSize;
  /**
   * The {@link Flux} on which buffers are published.
   */
  private final Flux<List<G>> bufferedGameProcessor;
  /**
   * The {@link reactor.core.publisher.TopicProcessor} on which game states are published.
   */
  private final FluxProcessor<G, G> gameProcessor = TopicProcessor.create();

  /**
   * Constructs a new buffered {@link Strategizer} with the supplied buffer size.
   *
   * @param bufferSize The buffer size to use.
   */
  public BufferedStrategizer(final int bufferSize) {
    this.bufferSize = bufferSize;
    this.bufferedGameProcessor = this.gameProcessor
        .buffer(this.getBufferSize(), 1)
        .map(Lists::reverse);
  }

  @Override
  public S strategize(final G mostRecentGame) {
    this.getGameProcessor().onNext(mostRecentGame);
    return this.strategize(this.getBufferedGameProcessor().blockFirst());
  }

  public abstract S strategize(final List<G> mostRecentGame);
}
