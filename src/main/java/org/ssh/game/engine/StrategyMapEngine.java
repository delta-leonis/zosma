package org.ssh.game.engine;

import org.ssh.game.Agent;
import org.ssh.game.Command;
import org.ssh.game.Game;
import org.ssh.game.Strategizer;
import org.ssh.game.Strategy;
import org.ssh.math.function.Scanner;

/**
 * The Interface StrategyMapEngine. <p> This interface describes the functionality of a {@link
 * MapEngine}* which produces {@link Strategy} for a specific semantic triple ({@link Agent}, {@link
 * Command}*, {@link Game}). The {@link Scanner} inside this {@link Engine} is mapped to a specific
 * identifying value.
 *
 * @param <M> The type of identifier for embedded {@link Scanner}.
 * @param <S> the type parameter
 * @param <G> The type of {@link Game} for which {@link Strategy} is being generated.
 * @author Rimon Oz
 */
public interface StrategyMapEngine<M, S extends Strategy, G extends Game>
    extends MapEngine<M, S, Strategizer<S, G>>,
    StrategyEngine<S, G> {

}
