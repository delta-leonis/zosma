package org.ssh.game.engine;

import org.reactivestreams.*;
import reactor.core.publisher.*;

/**
 * The Interface FeedbackAI.
 *
 * This interface describes the functionality of an {@link AI} which feeds the output
 * it creates into a {@link Publisher} of the input type, thus creating
 * a feedback loop which can be used for simulating game play without an external game state
 * processor.
 *
 * @param <I> The type of input which this {@link AI} can receive.
 * @param <O> The type of output produced by this {@link AI}.
 * @param <C> The type of parts of which this {@link AI} consists.
 * @author Rimon Oz
 */
public interface FeedbackAI<I, O, C> extends AI<I, O, C> {

  /**
   * Projects an input state based on a previously projected input state and most recently
   * produced output state.
   *
   * @param previousInput The previously projected input state.
   * @param latestOutput  The latest output produced by the {@link AI}.
   * @return The projected input state.
   */
  I project(final I previousInput, final O latestOutput);

  @Override
  default void play() {
    Flux.combineLatest(this.getInputProcessor(), this.getOutputProcessor(), this::project)
        .startWith(this.getInitialInput())
        .sampleMillis(this.getInputProcessorInterval())
        .doOnNext(this.getInputProcessor()::onNext)
        .transform(inputPublisher -> this.apply(this.getEngineContainer(), inputPublisher))
        .subscribe(this.getOutputProcessor());
  }

  @Override
  default Publisher<I> getInputPublisher() {
    return this.getInputProcessor();
  }

  @Override
  default Subscriber<O> getOutputSubscriber() {
    return this.getOutputProcessor();
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
   * @return The interval on which the {@link TopicProcessor game processor} publishes input.
   */
  long getInputProcessorInterval();
}
