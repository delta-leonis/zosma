package org.ssh.game.engine;

import java.util.function.Function;
import lombok.Value;
import org.reactivestreams.*;
import reactor.core.publisher.*;

/**
 * The Interface Deducer.
 *
 * This interface describes the functionality of an engine, ie. an {@link Function} from {@link
 * Publisher publishers of} input states to {@link Publisher publishers of} output states.
 *
 * @param <I> The type of input interpreted by this Deducer.
 * @param <O> The type of output produced by this Deducer.
 * @author Rimon Oz
 */
public interface Deducer<I, O> extends Function<Publisher<I>, Publisher<O>> {

  class Identity<I> implements Deducer<I, I> {
    @Override
    public Publisher<I> apply(final Publisher<I> inputPublisher) {
      return inputPublisher;
    }
  }

  /**
   * The Class Tap.
   *
   * This class represents an intermediary {@link Subscriber}.
   *
   * @param <I> The type of input supplied to the {@link Tap}.
   * @author Rimon Oz
   */
  @Value
  class Tap<I> implements Deducer<I, I> {
    private final Subscriber<I> subscriber;
    @Override
    public Publisher<I> apply(final Publisher<I> inputPublisher) {
      final TopicProcessor<I> topicProcessor = TopicProcessor.create();
      Flux.from(inputPublisher).subscribe(topicProcessor);
      topicProcessor.subscribe(this.subscriber);
      return topicProcessor;
    }
  }
}
