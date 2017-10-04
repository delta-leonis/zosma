package io.leonis.zosma.game.engine;

import org.reactivestreams.Publisher;
import reactor.core.publisher.*;

/**
 * The Interface FeedbackDeducer.
 *
 * This interface describes the functionality of an {@link ParallelDeducer} which feeds the output
 * it creates into a {@link Publisher} of the input type, thus creating a feedback loop which can be
 * used for simulating game start without an external game state processor.
 *
 * @param <I> The type of input which this {@link ParallelDeducer} can receive.
 * @param <O> The type of output produced by this {@link ParallelDeducer}.
 * @author Rimon Oz
 */
public interface FeedbackDeducer<I, O> extends Deducer<I, O> {

  /**
   * Projects an input state based on a previously projected input state and most recently produced
   * output state.
   *
   * @param previousInput The previously projected input state.
   * @param latestOutput  The latest output produced by the {@link ParallelDeducer}.
   * @return The projected input state.
   */
  I project(final I previousInput, final O latestOutput);

  default Flux<O> start() {
    return Flux.from(this.apply(
        Flux.combineLatest(this.getInputProcessor(), this.getOutputProcessor(), this::project)
            .startWith(this.getInitialInput())
            .sampleMillis(this.getInputProcessorInterval())
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
   * @return The interval on which the {@link TopicProcessor game processor} publishes input.
   */
  long getInputProcessorInterval();
}
