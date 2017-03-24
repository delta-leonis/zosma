package org.ssh.game.engine;

import org.ssh.game.*;
import org.ssh.math.function.Scanner;

/**
 * The Interface StrategyMapEngine.
 * <p>
 * This interface describes the functionality of a {@link MapEngine} which produces
 * {@link Strategy} for a specific semantic triple ({@link Agent}, {@link Command}, {@link Game}). The {@link Scanner}
 * inside this {@link Engine} is mapped to a specific identifying value.
 *
 * @param <M> The type of identifier for embedded {@link Scanner}.
 * @param <A> The type of {@link Agent} for which the strategy strategy creates {@link Strategy}.
 * @param <C> The type of {@link Command} which can be understood by an {@link Agent}.
 * @param <G> The type of {@link Game} for which {@link Strategy} is being generated.
 * @author Rimon Oz
 */
public interface StrategyMapEngine<M, S extends Strategy<? extends Agent, ? extends Command, G>, G extends Game>
        extends MapEngine<M, S, Strategizer<S, G>>,
        StrategyEngine<S, G> {
}
