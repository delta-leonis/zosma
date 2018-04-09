package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.Field;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class OutOfBoundsRule.
 *
 * This class describes the rule in Small Size League soccer which
 * determines whether an object inside the sequence state is out of bounds.
 *
 * @param <O> The type of object which can violate the rule.
 * @author Rimon Oz
 */
public interface OutOfBoundsRule<I extends Field.Supplier, O> extends Rule<I, O> {

  @Override
  default Set<O> getViolators(final I input) {
    return this.getObjectsFromState(input).stream()
        .filter(ball -> this.test(input.getField(), ball))
        .collect(Collectors.toSet());
  }

  /**
   * Retrieves possible violators of this rule from the input object.
   *
   * @param input The input to check for violations.
   * @return A {@link Set} of objects which are in violation of the rule.
   */
  Set<O> getObjectsFromState(final I input);

  /**
   * Determines whether a supplied object is out of bounds.
   *
   * @param sslField         The {@link Field} to use as boundary.
   * @param possibleViolator The object to check whether it is within bounds.
   * @return True if within bounds, false otherwise.
   */
  boolean test(final Field sslField, final O possibleViolator);

  @Override
  default boolean test(final I input) {
    return this.getObjectsFromState(input).stream()
        .anyMatch(ball -> this.test(input.getField(), ball));
  }
}
