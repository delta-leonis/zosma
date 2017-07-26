package org.ssh.game.engine;

import java.util.function.BiFunction;
import org.reactivestreams.*;
import org.ssh.benchmarks.Probeable;
import reactor.core.publisher.Flux;

/**
 * The Interface AI.
 *
 * This interface describes the functionality of an AI which takes a {@link Flux} of
 * input and transforms them into output.
 *
 * @author Rimon Oz
 */
public interface AI<I, O, C> extends BiFunction<C, Publisher<I>, Publisher<O>>, Probeable {
  /**
   * Starts the AI and makes it play a game.
   */
  default void play() {
    Flux.from(this.getInputPublisher())
        .doOnNext(createProbeTarget("input"))
        .transform(inputPublisher -> this.apply(this.getEngineContainer(), inputPublisher))
        .doOnNext(createProbeTarget("output"))
        .subscribe(this.getOutputSubscriber());
  }

  /**
   * @return The input {@link Publisher}.
   */
  Publisher<I> getInputPublisher();

  /**
   * @return An object from which one or more {@link Engine Engines} may be extracted.
   */
  C getEngineContainer();

  /**
   * @return The output {@link Subscriber}.
   */
  Subscriber<O> getOutputSubscriber();
}
