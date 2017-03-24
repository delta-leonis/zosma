package org.ssh.game;

import java.util.Map;

/**
 * The Interface Strategy.
 * <p>
 * This interface describes the functionality of a strategic plan for a game.
 * A strategic plan is a set of subject-predicate-object relationships
 * describing the instructions which should be performed in order to
 * successfully complete the strategy.
 *
 * @param <A> The type of object (subject) which can interpret
 *            instructions in this strategy.
 * @param <C> The type of command (predicate) which can be issued
 *            by this strategy.
 * @param <G> The type of {@link Game} for which this strategy is meant.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Strategy<
        A extends Agent,
        C extends Command,
        G extends Game> {
    /**
     * Returns the instructions encapsulated by this strategy.
     *
     * @return A {@link Map} of instructions.
     */
    Map<A, C> getInstructions();
}
