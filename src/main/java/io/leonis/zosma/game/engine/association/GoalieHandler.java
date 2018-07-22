package io.leonis.zosma.game.engine.association;

import io.leonis.zosma.game.data.*;
import java.util.Set;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

/**
 * The Class GoalieHandler.
 *
 * Handles the bahaviour of a Goalie.
 *
 * @author Jeroen de Jong
 */
public class GoalieHandler implements RoleHandler {

  @Override
  public PlayerCommand apply(
      final Game game,
      final AllegianceTuple<Team> teams,
      final AllegianceTuple<Set<MovingPlayer>> players,
      final MovingBall ball,
      final Field field,
      final AllegianceTuple<Goal> goals,
      final MovingPlayer agent
  ) throws Exception {
    return new PlayerCommand.State(
        new NDArray(),0, 0, 0);
  }
}
