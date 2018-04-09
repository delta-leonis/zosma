package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.data.*;
import java.util.Set;

/**
 * The Class PlayerOutOfBoundsRule.
 *
 * This class describes the rule in Small Size League soccer
 * which determines whether a robot is within the bounds of the field.
 *
 * @author Rimon Oz
 */
public class PlayerOutOfBoundsRule<I extends Field.Supplier & Player.SetSupplier>
    implements OutOfBoundsRule<I, Player> {

  @Override
  public Set<Player> getObjectsFromState(final I input) {
    return input.getPlayers();
  }

  @Override
  public boolean test(final Field field, final Player possibleViolator) {
    return possibleViolator.getX() > field.getLength() / 2f
        || possibleViolator.getX() < field.getLength() / -2f
        || possibleViolator.getY() > field.getWidth() / 2f
        || possibleViolator.getY() < field.getWidth() / -2f;
  }
}
