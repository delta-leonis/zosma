package io.leonis.zosma.game.engine;

import java.time.Duration;
import org.reactivestreams.*;
import reactor.core.publisher.*;

/**
 * The Interface GameSimulator.
 *
 * This interface describes the functionality of an game simulator. A game is simulated by acting on
 * all received input, and publishing its initial state to itself, thus creating a feedback loop
 * which can be used for simulating game start without an external game state processor.
 *
 * @param <I> The type of game state on which this simulator acts.
 * @param <O> The type of strategic output produced by this simulator.
 * @author Rimon Oz
 */
public interface GameSimulator<I, O> extends Deducer<I, O>, Publisher<O> {
  @Override
  default void subscribe(Subscriber<? super O> s) {
    Flux.from(this.apply(
        Flux.combineLatest(this.getInputProcessor(), this.getOutputProcessor(), this::project)
            .startWith(this.getInitialInput())
            .sample(Duration.ofMillis(this.getInputProcessorIntervalMillis()))
            .doOnNext(this.getInputProcessor()::onNext)));
  }

  /**
   * @return The {@link TopicProcessor processor} which forwards outputs.
   */
  TopicProcessor<I> getInputProcessor();

  /**
   * @return The {@link TopicProcessor processor} which forwards outputs.
   */
  TopicProcessor<O> getOutputProcessor();

  /**
   * @return The initial input.
   */
  I getInitialInput();

  /**
   * @return The interval on which the {@link TopicProcessor game processor} publishes input in ms.
   */
  long getInputProcessorIntervalMillis();

  /**
   * Projects an input state based on a previously projected input state and most recently produced
   * output state.
   *
   * @param previousInput The previously projected input state.
   * @param latestOutput  The latest output produced by the {@link ParallelDeducer}.
   * @return The projected input state.
   */
  I project(final I previousInput, final O latestOutput);
}
