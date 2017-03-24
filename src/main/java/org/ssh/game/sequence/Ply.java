package org.ssh.game.sequence;

import org.ssh.game.Game;

/**
 * The Interface Ply.
 * <p>
 * A Ply is a two-player sequential, ie. turn-based, game. Examples of plygames are chess.
 *
 * @author Rimon Oz
 */
public interface Ply extends Game {

  /**
   * Indicates whether the Ply has been played.
   *
   * @return True if played, false otherwise.
   */
  boolean isHistory();
}
