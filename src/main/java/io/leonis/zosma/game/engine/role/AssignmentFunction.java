package io.leonis.zosma.game.engine.role;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.reactivex.functions.Function3;
import java.time.Duration;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * The Class
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class AssignmentFunction
    implements Function3<Set<Assignment>, Set<MovingPlayer>, Team, Set<Assignment>> {

  private final BiFunction<Player, Team, Assignment> createAssignment;

  public AssignmentFunction(final Duration lifetime, final BiFunction<Team, Player, Role> roleFunction
  ) {
    this.createAssignment = (player, ally) -> new Assignment(lifetime, player.getIdentity(), roleFunction.apply(ally, player));
  }

  @Override
  public Set<Assignment> apply(
      final Set<Assignment> previousAssignments,
      final Set<MovingPlayer> players,
      final Team ally
  ) {
    Map<PlayerIdentity, Assignment> existingAssignments = previousAssignments.stream()
        .filter(Assignment::isUnexpired)
        .collect(Collectors.toMap(
            Assignment::getAssignee,
            Function.identity()));
    // FIXME this computes the assignment for every iteration; undesirable
    return players.stream()
        .map(player -> existingAssignments.getOrDefault(player.getIdentity(), createAssignment.apply(player, ally)))
        .collect(Collectors.toSet());
  }
}
