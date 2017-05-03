package org.ssh.game.engine;

import org.reactivestreams.Publisher;
import org.ssh.game.Game;
import org.ssh.game.Strategy;
import reactor.core.publisher.Flux;

/**
 * The Interface NoisyFeedbackAI.
 *
 * This interface describes the functionality of an {@link AI} which feeds applies noise to
 * the {@link Strategy strategies} it creates and feeds them back into a {@link Publisher} of
 * {@link Game game states}, thus creating a feedback loop which can be used for simulating game
 * play without an external game state processor.
 *
 * @param <S> The type of {@link Strategy} produced by this {@link AI}.
 * @param <G> The type of {@link Game} which this {@link AI} can play.
 * @param <P> The type of parts of which this {@link AI} consists.
 * @author Rimon Oz
 */
public interface NoisyFeedbackAI<S extends Strategy, G extends Game, P>
    extends FeedbackAI<S, G, P> {

  @Override
  default void play() {
    Flux.combineLatest(this.getGameProcessor(), this.getStrategyProcessor(), this::project)
        .startWith(this.getInitialGame())
        .sampleMillis(this.getGameProcessorInterval())
        .doOnNext(this.getGameProcessor()::onNext)
        .transform(this::apply)
        .map(this::applyNoise)
        .subscribe(this.getStrategyProcessor());
  }

  /**
   * Distorts the {@link Strategy} by applying noise to it.
   * @param strategy The {@link Strategy} to apply noise to.
   * @return The noisy {@link Strategy}.
   */
  S applyNoise(S strategy);
}
