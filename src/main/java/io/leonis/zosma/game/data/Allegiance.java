package io.leonis.zosma.game.data;

/**
 * The Enum Allegiance.
 *
 * Describes the Allegiance of an object.
 *
 * @author Jeroen de Jong
 */
public enum Allegiance {
  ALLY, OPPONENT;

  /**
   * @return Opponent of current team (Ally for Opponent and vise versa)
   */
  public Allegiance opponent() {
    return this.equals(ALLY) ? OPPONENT : ALLY;
  }
}
