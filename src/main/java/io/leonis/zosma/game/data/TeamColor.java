package io.leonis.zosma.game.data;

/**
 * The Enum TeamColor.
 *
 * Describes the color of a team. 
 *
 * @author Jeroen de Jong
 */
public enum TeamColor {
  YELLOW, BLUE;

  /**
   * @return Color of opponent team
   */
  public TeamColor opponent() {
    return this.equals(BLUE) ? YELLOW : BLUE;
  }
}
