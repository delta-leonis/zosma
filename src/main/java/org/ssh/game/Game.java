package org.ssh.game;

import java.util.Set;

/**
 * The Interface Game. <p> This interface describes the functionality of a game. The functionality
 * of this game is inspired by <a href="http://www.thegamesjournal.com/articles/WhatIsaGame.shtml">this
 * article's introduction</a>.
 *
 * @author Rimon Oz
 */
public interface Game {

  /**
   * Returns a set of {@link Rule} which dictate the validity of a game state.
   *
   * @return A set of {@link Rule} which dictate the validity of a game state.
   */
  Set<Rule<Game, Agent>> getRules();

  /**
   * Returns the initial game state, ie. the state of the game as it is in the moment at which
   * the game is started.
   *
   * @return The initial state of the game.
   */
  Game getInitialState();
}
