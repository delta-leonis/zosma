package org.ssh.io;

import java.util.Set;
import org.ssh.game.*;

/**
 * The Interface RuleSupplier.
 *
 * See <a href="http://www.thegamesjournal.com/articles/WhatIsaGame.shtml">this
 * article's introduction</a>.
 *
 * @author Rimon Oz
 */
public interface RuleSupplier<G, A extends Agent> {

  /**
   * @return A set of {@link Rule rules} which dictate the validity of a game state.
   */
  Set<Rule<G, A>> getRules();
}
