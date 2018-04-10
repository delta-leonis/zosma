package io.leonis.zosma.game.data;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.Identifiable;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import java.io.Serializable;
import java.util.List;
import lombok.Value;

/**
 * The Interface Team.
 *
 * This interface describes the functionality of a team in a Small Size League game.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface Team extends Identifiable, Serializable, Temporal {

  /**
   * @return The {@link TeamIdentity identity} of the team.
   */
  TeamIdentity getIdentity();

  /**
   * @return The name of the team.
   */
  String getName();

  /**
   * @return The number of goals scored since the beginning of the game.
   */
  int getScore();

  /**
   * @return The amount of red cards issued to the team.
   */
  int getRedCardCount();

  /**
   * @return The amount of yellow cards issued to the team.
   */
  int getYellowCardCount();

  /**
   * @return Timestamps (in seconds) describing time since epoch when yellow cards were issued.
   */
  List<Integer> getYellowCards();

  /**
   * @return The number of time outs left for this team.
   */
  int getTimeOutsLeft();

  /**
   * @return The time out time left for this team.
   */
  long getTimeOutTimeLeft();

  /**
   * @return The identity of the goalie.
   */
  PlayerIdentity getGoalie();

  /**
   * The Interface TeamIdentity.
   *
   * Describes the identity of a Team.
   *
   * @author Jeroen de Jong
   */
  interface TeamIdentity {

    /**
     * @return The color that identifies this team.
     */
    String getColor();
  }

  @Value
  class State implements Team {
    private final long timestamp;
    private final String name;
    private final int score;
    private final int redCardCount;
    private final int yellowCardCount;
    private final List<Integer> yellowCards;
    private final int timeOutsLeft;
    private final long timeOutTimeLeft;
    private final PlayerIdentity goalie;
    private final TeamIdentity identity;
  }
}
