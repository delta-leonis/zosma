package org.ssh.io;

import java.util.Map;
import org.ssh.game.Agent;

/**
 * The Interface StrategySupplier.
 *
 * @param <A> The type of {@link Agent} for which this strategy is meant.
 * @param <C> The type of command which the {@link Agent} can interpret.
 * @author Rimon Oz
 */
public interface StrategySupplier<A extends Agent, C> {
  Map<A, C> getStrategy();
}
