package io.leonis.zosma.game.engine.association;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.*;
import java.util.*;
import lombok.Value;

/**
 * The Interface AssociationStrategy.
 *
 * Describes a {@link Strategy} produces by the {@link AssociationEngine}. Contains the commands and
 * the assignment set.
 *
 * @author Jeroen de Jong
 */
public interface AssociationStrategy extends Strategy, Temporal {
  Set<Assignment> getAssignments();

  @Value
  class State implements AssociationStrategy {
    private final Set<Assignment> assignments;
    private final Map<PlayerIdentity, PlayerCommand> commands;
    private final long timestamp = System.currentTimeMillis();
  }
}
