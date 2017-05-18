package org.ssh.game.engine;

import org.reactivestreams.*;
import org.ssh.game.*;
import reactor.core.publisher.*;

/**
 * The Interface FeedbackAI.
 *
 * This interface describes the functionality of an {@link AI} which feeds the {@link Strategy
 * strategies} it creates into a {@link Publisher} of {@link Game game states}, thus creating
 * a feedback loop which can be used for simulating game play without an external game state
 * processor.
 *
 * @param <S> The type of {@link Strategy} produced by this {@link AI}.
 * @param <G> The type of {@link Game} which this {@link AI} can play.
 * @param <P> The type of parts of which this {@link AI} consists.
 * @author Rimon Oz
 */
public interface FeedbackAI<S extends Strategy, G extends Game, P> extends AI<S, G, P> {

  @Override
  default Subscriber<S> getStrategySubscriber() {
    return this.getStrategyProcessor();
  }

  /**
   * @return The {@link TopicProcessor processor} which forwards {@link Strategy strategies}.
   */
  TopicProcessor<S> getStrategyProcessor();

  /**
   * Computes the new {@link Game game state} based on the previous game state and latest
   * {@link Strategy}.
   *
   * @param previousGameState The latest known {@link Game game state}.
   * @param newestStrategy    The newest {@link Strategy} computed by the AI.
   * @return The newest {@link Game game state} by applying the {@link Strategy}.
   */
  G project(final G previousGameState, final S newestStrategy);

  @Override
  default void play() {
    Flux.combineLatest(this.getGameProcessor(), this.getStrategyProcessor(), this::project)
        .startWith(this.getInitialGame())
        .sampleMillis(this.getGameProcessorInterval())
        .doOnNext(this.getGameProcessor()::onNext)
        .transform(this::apply)
        .subscribe(this.getStrategyProcessor());
  }

  @Override
  default Publisher<G> getGamePublisher() {
    return this.getGameProcessor();
  }

  /**
   * @return The {@link TopicProcessor processor} which forwards {@link Strategy strategies}.
   */
  TopicProcessor<G> getGameProcessor();

  /**
   * @return The interval on which the {@link TopicProcessor game processor} publishes {@link Game
   * game states}.
   */
  long getGameProcessorInterval();
}
