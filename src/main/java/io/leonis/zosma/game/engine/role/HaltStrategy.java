package io.leonis.zosma.game.engine.role;

import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.PlayerCommand;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Value;

/**
 * The Class HaltStrategy.
 *
 * Contains behaviour in case of an emergence HALT.
 *
 * @author Jeroen de Jong
 */
@Value
public class HaltStrategy implements RoleBasedStrategy {
  private final long timestamp = System.currentTimeMillis();
  private final Set<Assignment> assignments;

  @Override
  public Map<PlayerIdentity, PlayerCommand> getCommands() {
     return assignments.stream()
          .map(Assignment::getAssignee)
         .collect(Collectors.toMap(Function.identity(), p -> PlayerCommand.STOP));
  }
}
