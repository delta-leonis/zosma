package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.Command;
import java.util.*;

/**
 * The Class InDirectFreeKickRule.
 *
 * @author Rimon Oz
 */
public class InDirectFreeKickRule implements Rule<Referee.Supplier, TeamColor> {

  @Override
  public boolean test(final Referee.Supplier sslReferee) {
    return !this.getViolators(sslReferee).isEmpty();
  }

  @Override
  public Set<TeamColor> getViolators(final Referee.Supplier sslReferee) {
    if (sslReferee.getReferee().getCommand()
        .equals(Command.INDIRECT_FREE_BLUE)) {
      return Collections.singleton(TeamColor.BLUE);
    } else if (sslReferee.getReferee().getCommand()
        .equals(Command.INDIRECT_FREE_YELLOW)) {
      return Collections.singleton(TeamColor.YELLOW);
    }
    return Collections.emptySet();
  }
}
