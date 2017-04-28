package org.ssh.game;

import org.reactivestreams.Subscriber;

/**
 * The Interface Strategizer.
 * <p>
 * This interface describes the functionality of an object which
 * {@link Subscriber subscribes} to {@link Game a game} and produces a
 * {@link Strategy} based on that {@link Game}.
 *
 * @param <S> The type of {@link Strategy} emitted by this strategizer.
 * @param <G> The type of {@link Game} for which this strategy is meant.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Strategizer<S extends Strategy, G extends Game> {

  /**
   * Computes a {@link Strategy} based on the previously computed {@link Strategy} and
   * most recent {@link Game game state}.
   *
   * @param previousResult The previously returned {@link Strategy}.
   * @param input          The newest {@link Game game state}.
   * @return               The newly computed {@link Strategy}.
   */
  S strategize(final S previousResult, final G input);
}
