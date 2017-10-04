package io.leonis.zosma.game.engine;

import java.util.function.Function;
import lombok.Value;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * The Class Zosma.
 *
 * This class represents an AI supervisor, ie. an object which holds an AI and manages its runtime.
 * The supervisor also holds adapters for making sure that the supplied AI can attach itself to
 * system input and output.
 *
 * @param <I> The type of input accepted by the system.
 * @param <J> The type of input accepted by the AI.
 * @param <Q> The type of output produced by the AI.
 * @param <R> The type of output produced by the system.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class Zosma<I, J, Q, R> implements Runnable {
  private final Publisher<I> inputPublisher;
  private final Function<Publisher<I>, Publisher<J>> inputAdapter;
  private final Function<Publisher<J>, Publisher<Q>> ai;
  private final Function<Publisher<Q>, Publisher<R>> outputAdapter;
  private final Subscriber<R> outputSubscriber;

  @Override
  public void run() {
    Flux.from(inputPublisher)
        .transform(this.inputAdapter)
        .transform(this.ai)
        .transform(this.outputAdapter)
        .subscribe(this.outputSubscriber);
  }
}
