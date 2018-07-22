package io.leonis.zosma.game.data;

/**
 * The Enum TeamColor.
 *
 * Describes the color of a team. Note that most logic should be based on the {@link Allegiance} of
 * a {@link Team} in order to take switching colors of teams into account.
 *
 * @author Jeroen de Jong
 */
public enum TeamColor {
  YELLOW, BLUE;

  /**
   * @return Color of opponent team.
   */
  public TeamColor opponent() {
    return this.equals(BLUE) ? YELLOW : BLUE;
  }
}
