package org.ssh.game;

import java.util.Set;
import java.util.function.Predicate;

/**
 * The Interface Rule.
 * <p>
 * This interface describes the functionality of a rule which is used to
 * interpret the validity of a {@link Game}.
 *
 * @param <G> The type of {@link Game game state} to which this rule may be applied.
 * @param <V> The type of objects which can be in violation of the rule.
 * @author Rimon Oz
 */
public interface Rule<G extends Game, V> extends Predicate<G> {
    /**
     * Returns the objects in the {@link Game game state} which are in violation of the rule.
     *
     * @param game The {@link Game game state} to check for violators.
     * @return The objects in the {@link Game game state} which are in violation of the rule.
     */
    Set<V> getViolators(G game);
}
