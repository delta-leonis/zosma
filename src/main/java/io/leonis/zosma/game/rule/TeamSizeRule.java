package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;

/**
 * The Class TeamSizeRule.
 *
 * @author Rimon Oz
 */
@Value
public class TeamSizeRule implements Rule<Player.SetSupplier, TeamColor> {
  private final int teamSize;

  @Override
  public Set<TeamColor> getViolators(final Player.SetSupplier playerSupplier) {
    return playerSupplier.getPlayers().stream()
        .collect(Collectors.groupingBy(Player::getTeamColor))
        .entrySet().stream()
        .filter(entry -> entry.getValue().size() > this.teamSize || entry.getValue().isEmpty())
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  @Override
  public boolean test(final Player.SetSupplier playerSupplier) {
    return playerSupplier.getPlayers().stream()
        .collect(Collectors.groupingBy(Player::getTeamColor))
        .entrySet().stream()
        .anyMatch(
            entry -> entry.getValue().size() > this.teamSize || entry.getValue().isEmpty());
  }
}
