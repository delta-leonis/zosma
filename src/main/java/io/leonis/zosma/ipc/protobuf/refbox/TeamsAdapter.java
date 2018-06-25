package io.leonis.zosma.ipc.protobuf.refbox;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.TeamColor.*;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * The Class TeamsAdapter.
 *
 * Adapts a {@link SSL_Referee} into a an {@link AllegianceTuple} of {@link Team}.
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class TeamsAdapter implements Function<SSL_Referee, AllegianceTuple<Team>> {
  private final TeamAdapter allySelector = new TeamAdapter(ALLY);
  private final TeamAdapter opponentSelector = new TeamAdapter(OPPONENT);
  private final String allyTeamName;

  @Override
  public AllegianceTuple<Team> apply(final SSL_Referee referee) {
    if(referee.getBlue().getName().equalsIgnoreCase(allyTeamName) &&
        referee.getYellow().getName().equalsIgnoreCase(allyTeamName)) {
      throw new IllegalArgumentException("Both teams have the same name!");
    } else if(!referee.getBlue().getName().equalsIgnoreCase(allyTeamName) &&
        !referee.getYellow().getName().equalsIgnoreCase(allyTeamName)) {
      throw new IllegalArgumentException("Neither team has the ally team name!");
    }
    final TeamColor allyColor = referee.getBlue().getName().equalsIgnoreCase(allyTeamName) ? BLUE : YELLOW;
    return new AllegianceTuple<>(
      allySelector.apply(referee, allyColor),
      opponentSelector.apply(referee, allyColor.opponent()));
  }
}
