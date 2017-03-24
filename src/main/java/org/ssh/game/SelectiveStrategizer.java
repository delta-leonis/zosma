package org.ssh.game;

import org.reactivestreams.Subscriber;
import org.ssh.math.function.SelectiveScanner;

/**
 * The Interface SelectiveStrategizer.
 * <p>
 * This interface describes the functionality of an object which
 * {@link Subscriber subscribes} to {@link Game a game} and produces a
 * {@link Strategy} based on that {@link Game}.
 *
 * @param <M> The type of object used to identify an engine part.
 * @param <S> The type of {@link Strategy} emitted by this strategizer.
 * @param <G> The type of {@link Game} for which this strategy is meant.
 * @author Rimon Oz
 */
public interface SelectiveStrategizer<M, S extends Strategy, G extends Game>
    extends SelectiveScanner<S, G, M>, Strategizer<S, G> {

  @Override
  default S scan(S previousResult, G input) {
    return this.scan(previousResult, input, this.getSelection(input));
  }

  /**
   * Returns a selection of object(s) of interest for this {@link Strategizer} from the supplied
   * {@link Game}.
   *
   * @param game The {@link Game} to extract the object(s) of interest from.
   * @return The object(s) of interest.
   */
  M getSelection(G game);
}
