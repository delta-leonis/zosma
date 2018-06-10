package io.leonis.zosma.game.data;

import io.leonis.algieba.Temporal;
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
public interface Team extends Serializable, Temporal {

  /**
   * @return The name of the team.
   */
  String getName();

  /**
   * @return The identity of the goalie.
   */
  PlayerIdentity getGoalie();

  /**
   * @return The allegiance of this team.
   */
  Allegiance getAllegiance();

  /**
   * @return The most recent statistics of this team in the current game.
   */
  TeamData getTeamData();

  @Value
  class Teams implements Supplier<Team> {
    private final Team ally, opponent;
  }

  @Value
  class State implements Team {
    private final long timestamp;
    private final String name;
    private final int goalieId;
    private final Allegiance allegiance;
    private final TeamData teamData;

    @Override
    public PlayerIdentity getGoalie() {
      return new PlayerIdentity(goalieId, allegiance);
    }
  }

}
