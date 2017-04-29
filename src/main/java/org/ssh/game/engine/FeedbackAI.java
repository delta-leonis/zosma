package org.ssh.game.engine;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.ssh.game.Game;
import org.ssh.game.Strategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.TopicProcessor;

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
   * @param previousGameState The latest known {@link Game game state}.
   * @param newestStrategy    The newest {@link Strategy} computed by the AI.
   * @return                  The newest {@link Game game state} by applying the {@link Strategy}.
   */
  G simulate(final G previousGameState, final S newestStrategy);

  @Override
  default void play() {
    Flux.combineLatest(this.getGameProcessor(), this.getStrategyProcessor(), this::simulate)
        .startWith(this.getInitialGame())
        .sampleMillis(this.getGameProcessorInterval())
        .doOnNext(this.getGameProcessor()::onNext)
        .transform(this::apply)
        .delayElementsMillis(this.getStrategyTransmissionDelay())
        .delayElementsMillis(this.getStrategyExecutionDelay())
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

  /**
   * @return The time it takes for the {@link Strategy} to travel over the network on which
   * the {@link org.ssh.game.Agent agents} listen.
   */
  long getStrategyTransmissionDelay();

  /**
   * @return The time it takes for the {@link org.ssh.game.Agent agents} to parse the
   * {@link Strategy} and execute it.
   */
  long getStrategyExecutionDelay();
}
