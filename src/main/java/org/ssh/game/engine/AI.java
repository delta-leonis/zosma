package org.ssh.game.engine;

import java.util.function.Function;
import org.reactivestreams.*;
import org.ssh.io.ConfigSupplier;
import reactor.core.publisher.Flux;

/**
 * The Interface AI.
 *
 * This interface describes the functionality of an AI which takes a {@link Flux} of
 * input and transforms them into output.
 *
 * @param <I> The type of input interpreted by this AI.
 * @param <O> The type of output produced by this AI.
 * @author Rimon Oz
 */
public interface AI<I extends ConfigSupplier, O> extends Function<Publisher<I>, Publisher<O>> {
  /**
   * Starts the AI and makes it play a game.
   */
  default void play() {
    this.apply(this.getInputPublisher())
        .subscribe(this.getOutputSubscriber());
  }

  /**
   * @return The input {@link Publisher}.
   */
  Publisher<I> getInputPublisher();

  /**
   * @return The output {@link Subscriber}.
   */
  Subscriber<O> getOutputSubscriber();
}
