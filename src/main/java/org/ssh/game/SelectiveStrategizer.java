package org.ssh.game;

import org.reactivestreams.Subscriber;

/**
 * The Interface SelectiveStrategizer.
 * <p>
 * This interface describes the functionality of an object which
 * {@link Subscriber subscribes} to {@link Game a game} and produces a
 * {@link Strategy} based on that {@link Game}.
 *
 * @param <M> The type of object used to identify an engine part.
 * @param <S> The type of {@link Strategy} emitted by this strategizer.
 * @param <G> The type of {@link Game} for which this strategizer is meant.
 * @author Rimon Oz
 */
public interface SelectiveStrategizer<M, S extends Strategy, G extends Game>
    extends Strategizer<S, G> {

  @Override
  default S strategize(final G mostRecentGame) {
    return this.strategize(mostRecentGame, this.getSelection(mostRecentGame));
  }

  /**
   * Applies the strategizing operation to the previously returned engine, the newly
   * acquired game state, and the objects of interest which can be extracted from the game state.
   *
   * @param mostRecentGame The next {@link Game game state}.
   * @param subjects       The objects of interest in the {@link Game}.
   * @return The newly computed {@link Strategy}.
   */
  S strategize(final G mostRecentGame, final M subjects);

  /**
   * @param game The {@link Game} to extract the object(s) of interest from.
   * @return A selection of object(s) of interest for this {@link Strategizer} from the supplied
   * {@link Game}.
   */
  M getSelection(final G game);
}
