package io.leonis.zosma.game.engine.role;

import io.leonis.zosma.game.data.*;
import java.awt.Polygon;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import lombok.Value;

/**
 * The Class RoleFunction.
 *
 * Determines a {@link Role} based on the position of a {@link Player}. If the player is marked as
 * goalie, it's assign {@link Role GOALIE} regardless of position.
 *
 * @author Jeroen de Jong
 */
@Value
public class RoleFunction implements BiFunction<Team, Player, Role> {
  /** shapes over normalized field (6000x9000) */
  private final Map<Polygon, Role> areas;

  @Override
  public Role apply(final Team ally, final Player player) {
    if(ally.getGoalie() == player.getIdentity())
      return Role.GOALIE;

    return this.areas.entrySet().stream()
        .filter(entry -> entry.getKey().contains(player.getX(), player.getY()))
        .map(Entry::getValue)
        .findFirst().orElse(Role.NONE);
  }
}
