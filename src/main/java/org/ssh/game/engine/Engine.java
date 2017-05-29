package org.ssh.game.engine;

import java.util.function.Function;
import org.reactivestreams.*;
import org.ssh.game.*;

/**
 * The Interface Engine.
 *
 * This interface describes the functionality of an engine, ie. an {@link Function} from {@link
 * Publisher publishers of} {@link Game game states} to {@link Publisher publishers of} {@link
 * Strategy strategies} which can {@link Arbiter arbitrate} between {@link Strategy strategies}.
 *
 * @param <S> The type of {@link Strategy} produced by this Engine.
 * @param <G> The type of {@link Game} interpreted by this Engine.
 * @param <P> The type of parts of which this Engine consists.
 * @author Rimon Oz
 */
public interface Engine<
    S extends Strategy,
    G,
    P>
    extends Arbiter<S, P>, Function<Publisher<G>, Publisher<S>> {
  /**
   * @return The {@link Subscriber} which executes the {@link Strategy strategies}.
   */
  Subscriber<S> getStrategySubscriber();

  /**
   * @return The initial {@link Strategy}.
   */
  S getInitialStrategy();

  /**
   * Starts the engine and makes it play a game.
   */
  void play();
}
