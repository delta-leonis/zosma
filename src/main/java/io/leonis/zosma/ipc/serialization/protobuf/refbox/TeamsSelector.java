package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.TeamColor.BLUE;
import static io.leonis.zosma.game.data.TeamColor.YELLOW;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class TeamsSelector implements Function<SSL_Referee, AllegianceTuple<Team>> {
  private final TeamSelector allySelector = new TeamSelector(ALLY);
  private final TeamSelector opponentSelector = new TeamSelector(OPPONENT);
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
    final boolean allyIsBlue = referee.getBlue().getName().equalsIgnoreCase(allyTeamName);
    final TeamColor allyColor = allyIsBlue ? BLUE : YELLOW;
    return new AllegianceTuple<>(
      allySelector.apply(allyIsBlue ? referee.getBlue() : referee.getYellow(), allyColor, referee.getPacketTimestamp()),
      opponentSelector.apply(allyIsBlue ? referee.getYellow() : referee.getBlue(), allyColor.opponent(), referee.getPacketTimestamp()));
  }
}
