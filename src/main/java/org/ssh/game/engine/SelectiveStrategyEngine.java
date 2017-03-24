package org.ssh.game.engine;

import org.ssh.game.Agent;
import org.ssh.game.Command;
import org.ssh.game.Game;
import org.ssh.game.SelectiveStrategizer;
import org.ssh.game.Strategy;

/**
 * The Interface SelectiveStrategyEngine.
 * <p>
 * This interface describes the functionality of an {@link Engine} which produces
 * {@link Strategy} for a specific semantic triple ({@link Agent}, {@link Command}, {@link Game}).
 *
 * @param <M> The type of object used to identify an engine part.
 * @param <S> The type of {@link Strategy} which this {@link Engine} produces.
 * @param <G> The type of {@link Game} for which {@link Strategy} is being generated.
 * @author Rimon Oz
 */
public interface SelectiveStrategyEngine<M, S extends Strategy, G extends Game>
    extends ProcessorEngine<G, S, SelectiveStrategizer<M, S, G>>,
    SelectiveStrategizer<M, S, G> {

}
