package io.leonis.zosma.game.engine.role;

import com.google.common.collect.ImmutableMap;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Game.Command;
import io.reactivex.functions.Function7;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * The Class RoleBasedEngine.
 *
 * Assigns a {@link Role} to the provided {@link Player Players} accodring to the provided
 * assignmentFunciton. Dispatches handeling of the individual {@link Player} according to the
 * provided roleHandlers map.
 *
 * @author Jeroen de Jong
 */
// TODO maybe move players to team and make them allegiance-unaware
// TOOD do the same for the goal
@AllArgsConstructor
public class RoleBasedEngine implements
    Function7<RoleBasedStrategy, Game, AllegianceTuple<Team>, AllegianceTuple<Set<MovingPlayer>>, MovingBall, Field, AllegianceTuple<Goal>, RoleBasedStrategy> {

  private final AssignmentFunction assignFunction;
  private final Map<Role, RoleHandler> roleHandlers;

  public RoleBasedEngine() {
    this(new AssignmentFunction(Duration.ofSeconds(5), new RoleFunction(Collections.emptyMap())),
        ImmutableMap.of(Role.GOALIE, new GoalieHandler()));
  }

  @Override
  public RoleBasedStrategy apply(
      final RoleBasedStrategy previousStrategy,
      final Game game,
      final AllegianceTuple<Team> teams,
      final AllegianceTuple<Set<MovingPlayer>> players,
      final MovingBall ball,
      final Field field,
      final AllegianceTuple<Goal> goals
    ) throws Exception {
    Set<Assignment> assignments = assignFunction.apply(previousStrategy.getAssignments(), players.getAlly(), teams.getAlly());

    // FIXME this should be moved in a general command / checking class
    if(game.getCommand().equals(Command.HALT))
      return new HaltStrategy(assignments);

    return new RoleBasedStrategy.State(
        assignments,
        assignments.stream()
        .filter(assignment -> roleHandlers.containsKey(assignment.getRole()))
        .collect(Collectors.toMap(
            Assignment::getAssignee,
            assignment -> handle(game, teams, players, ball, field, goals, assignment))));
  }

  private PlayerCommand handle(
      final Game game,
      final AllegianceTuple<Team> teams,
      final AllegianceTuple<Set<MovingPlayer>> players,
      final MovingBall ball,
      final Field field,
      final AllegianceTuple<Goal> goals,
      final Assignment assignment
      ) {
    try{
      MovingPlayer player = players.getAlly().stream()
          .filter(p -> p.getIdentity().equals(assignment.getAssignee()))
          .findFirst().orElse(null); // FIXME this shouldn't happen, make sure it doesnt
      return roleHandlers.get(assignment.getRole()).apply(game, teams, players, ball, field, goals, player);
    } catch (Exception e) {
      throw new RuntimeException(e); // go on my child, reach the onError!
    }
  }
}
