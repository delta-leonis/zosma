package org.ssh.game;

import java.util.Set;

/**
 * The Interface Game. <p> This interface describes the functionality of a game. The functionality
 * of this game is inspired by <a href="http://www.thegamesjournal.com/articles/WhatIsaGame.shtml">this
 * article's introduction</a>.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Game<G extends Game, A extends Agent> {

  /**
   * @return A set of {@link Rule rules} which dictate the validity of a game state.
   */
  Set<Rule<G, A>> getRules();
}
