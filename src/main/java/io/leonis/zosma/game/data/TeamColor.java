package io.leonis.zosma.game.data;

import io.leonis.zosma.game.data.Team.TeamIdentity;

/**
 * The Enum TeamColor.
 *
 * This enumeration represents the possible colors a team can sport in a Small Size League
 * sequence.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public enum TeamColor implements TeamIdentity {
  /**
   * Blue team color.
   */
  BLUE,
  /**
   * Yellow team color.
   */
  YELLOW,
  /**
   * None team color.
   */
  NONE;

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColor() {
    return toString();
  }
}
