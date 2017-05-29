package org.ssh.game.engine;

import org.reactivestreams.Publisher;
import org.ssh.benchmarks.Probeable;
import org.ssh.game.*;
import reactor.core.publisher.Flux;

/**
 * The Interface AI.
 *
 * This interface describes the functionality of an AI which takes a {@link Flux} of
 * {@link Game game states} and transforms them into {@link Strategy} which are then
 * executed.
 *
 * @author Rimon Oz
 */
public interface AI<
    S extends Strategy,
    G extends Game,
    P>
    extends Engine<S, G, P>, Probeable {
  /**
   * Starts the AI and makes it play a game.
   */
  default void play() {
    Flux.from(this.getGamePublisher())
        .startWith(this.getInitialGame())
        .doOnNext(createProbeTarget("AI Input"))
        .transform(this::apply)
        .doOnNext(createProbeTarget("AI Output"))
        .subscribe(this.getStrategySubscriber());
  }

  /**
   * @return The {@link Publisher} of {@link Game game states}.
   */
  Publisher<G> getGamePublisher();

  /**
   * @return The initial {@link Game game state}.
   */
  G getInitialGame();
}
