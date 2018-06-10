package io.leonis.zosma.game.data;

import io.leonis.algieba.Temporal;
import java.util.List;
import lombok.Value;

/**
 * The Interface TeamData.
 *
 * Describes the statistics of a player during a game.
 *
 * @author Jeroen de Jong
 */
public interface TeamData extends Temporal {

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
   * @return The time out time left for this team in seconds.
   */
  int getTimeOutTimeLeft();


  @Value
  class State implements TeamData {
    private final int score;
    private final int redCardCount;
    private final int yellowCardCount;
    private final List<Integer> yellowCards;
    private final int timeOutsLeft;
    private final int timeOutTimeLeft;
    private final long timestamp;
  }
}
