package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Referee.Command;
import java.util.*;

/**
 * The Class DirectFreeKickRule.
 *
 * @author Rimon Oz
 */
public class DirectFreeKickRule implements Rule<Referee.Supplier, TeamColor> {

  @Override
  public boolean test(final Referee.Supplier refereeSupplier) {
    return !this.getViolators(refereeSupplier).isEmpty();
  }

  @Override
  public Set<TeamColor> getViolators(final Referee.Supplier refereeSupplier) {
    if (refereeSupplier.getReferee().getCommand()
        .equals(Command.DIRECT_FREE_BLUE)) {
      return Collections.singleton(TeamColor.BLUE);
    } else if (refereeSupplier.getReferee().getCommand()
        .equals(Command.DIRECT_FREE_YELLOW)) {
      return Collections.singleton(TeamColor.YELLOW);
    }
    return Collections.emptySet();
  }
}
