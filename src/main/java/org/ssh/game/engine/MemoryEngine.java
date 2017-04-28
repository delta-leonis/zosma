package org.ssh.game.engine;

import java.util.function.Function;
import org.reactivestreams.Publisher;
import org.ssh.math.function.Scanner;
import reactor.core.publisher.Flux;

/**
 * The Interface MemoryEngine.
 * <p>
 * This interface describes the functionality of an engine which accepts inputs
 * and processes them into outputs.
 *
 * @param <I> The type of input to the engine.
 * @param <O> The type of engine emissions.
 * @param <P> The type of engine parts.
 * @author Rimon Oz
 */
public interface MemoryEngine<I, O, P>
    extends Engine<O, P>, Function<Publisher<I>, Publisher<O>>, Scanner<O, I> {

  @Override
  default Publisher<O> apply(final Publisher<I> inputPublisher) {
    return Flux.from(inputPublisher)
        .scan(this.getInitialOutput(), this::scan);
  }

  /**
   * Returns the null-object (or the initial state) of the engine.
   *
   * @return The null-object (or the initial state) of the engine.
   */
  O getInitialOutput();
}