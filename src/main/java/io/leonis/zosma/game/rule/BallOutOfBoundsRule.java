package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.data.*;
import java.util.Set;

/**
 * The Class BallOutOfBoundsRule.
 *
 * This class describes the rule in Small Size League soccer
 * which determines whether the ball is within the bounds of the field.
 *
 * @author Rimon Oz
 */
public class BallOutOfBoundsRule<I extends Ball.SetSupplier & Field.Supplier>
    implements OutOfBoundsRule<I, Ball> {

  @Override
  public Set<Ball> getObjectsFromState(final I input) {
    return input.getBalls();
  }

  @Override
  public boolean test(final Field sslField, final Ball possibleViolator) {
    return possibleViolator.getX() > sslField.getLength() / 2f
        || possibleViolator.getX() < sslField.getLength() / -2f
        || possibleViolator.getY() > sslField.getWidth() / 2f
        || possibleViolator.getY() < sslField.getWidth() / -2f;
  }
}
