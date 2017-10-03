package io.leonis.game;

import java.util.Set;
import java.util.function.Predicate;

/**
 * The Interface Rule.
 *
 * This interface describes the functionality of a rule which is used to interpret the validity of
 * an instance.
 *
 * @param <I> The type of input to which this rule applies.
 * @param <V> The type of objects which can be in violation of the rule.
 * @author Rimon Oz
 */
public interface Rule<I, V> extends Predicate<I> {

  /**
   * @param input to check for violators.
   * @return The objects in the object which are in violation of the rule.
   */
  Set<V> getViolators(final I input);

  interface SetSupplier<G, A> {
    Set<Rule<G, A>> getRules();
  }
}
