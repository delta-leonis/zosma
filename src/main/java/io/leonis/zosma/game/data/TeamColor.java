package io.leonis.zosma.game.data;

/**
 * @author jeroen.dejong.
 * FIXME should be removed as most logic should rely on allegiance
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
