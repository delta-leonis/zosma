package io.leonis.zosma.game.data;

import io.leonis.zosma.game.data.Player.PlayerIdentity;
import java.util.Map;

/**
 * The Interface Strategy.
 *
 * @author Rimon Oz
 */
public interface Strategy {
  Map<PlayerIdentity, PlayerCommand> getAssignments();
}
