package org.ssh.io;

import java.util.Set;
import org.ssh.game.*;

/**
 * The Interface RuleSupplier.
 *
 * See <a href="http://www.thegamesjournal.com/articles/WhatIsaGame.shtml">this article's
 * introduction</a>.
 *
 * @param <G> The type of game state.
 * @param <A> The type of {@link Agent} which is embedded in the game.
 * @author Rimon Oz
 */
public interface RuleSupplier<G, A extends Agent> {

  /**
   * @return A set of {@link Rule rules} which dictate the validity of a game state.
   */
  Set<Rule<G, A>> getRules();
}
