package org.ssh.game.engine;

import org.reactivestreams.Subscriber;
import org.ssh.game.Strategy;

/**
 * The Interface Turk.
 *
 * This interface describes the functionality of a mechanical turk, ie. a participant in a
 * {@link org.ssh.game.Game} which is driven by a mechanism which is independent of the game state.
 *
 * @param <S> The type of {@link Strategy} produced by this Turk.
 * @param <P> The type of parts of which this Turk consists.
 * @author Rimon Oz
 */
public interface Turk<
    S extends Strategy,
    P>
    extends Engine<S, P> {
  /**
   * @return The {@link Subscriber} which executes the {@link Strategy strategies}.
   */
  Subscriber<S> getStrategySubscriber();

  /**
   * @return The initial {@link Strategy}.
   */
  S getInitialStrategy();

  /**
   * Starts the turk and makes it play a game.
   */
  void play();
}
