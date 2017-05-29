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
 * @param <G> The type of (game) object for which this engine is meant.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Strategizer<S extends Strategy, G> {

  /**
   * Computes a {@link Strategy} based on the most recent {@link Game game state}.
   *
   * @param mostRecentGame   The newest {@link Game game state}.
   * @return                 The newly computed {@link Strategy}.
   */
  S strategize(final G mostRecentGame);
}
