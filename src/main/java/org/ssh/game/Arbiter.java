package org.ssh.game;

import java.util.*;

/**
 * The Interface Arbiter.
 * <p>
 * This interface describes the functionality of an arbiter, that is an actor
 * which returns from a set of values which can be arbitrated a single value.
 *
 * @param <A> The type of object which this arbiter can arbitrate.
 * @param <O> The type of object from which the arbitrated values originate.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Arbiter<A, O> {

  /**
   * Arbitrates a {@link Set} of values and returns a single value.
   *
   * @param inputMap The set of values to arbitrate.
   * @return A single value.
   */
  A arbitrate(Map<O, A> inputMap);
}
