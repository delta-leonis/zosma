package io.leonis.zosma.game.rule;

import io.leonis.zosma.game.Rule;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class BallInGoalRule.
 *
 * This class describes the rule in a Small Size League sequence which
 * determines whether or not a ball is in a goal.
 *
 * @author Rimon Oz
 */
public class BallInGoalRule<I extends Goal.SetSupplier & Ball.SetSupplier>
    implements Rule<I, TeamIdentity> {

  @Override
  public Set<TeamIdentity> getViolators(final I input) {
    return input.getBalls().stream()
        .flatMap(ball ->
            input.getGoals().stream()
                .filter(goal -> this.isBallInGoal(ball, goal))
                .map(Goal::getTeamIdentity))
        .collect(Collectors.toSet());
  }

  /**
   * Determines whether a given {@link Ball} is within the given {@link Goal}.
   *
   * @param ball The {@link Ball} to determine whether or not is within the bounds of the {@link
   *             Goal}.
   * @param goal The {@link Goal} to determine whether or not the {@link Ball} is within its
   *             bounds.
   * @return True if the ball is in the goal, false otherwise.
   */
  public boolean isBallInGoal(final Ball ball, final Goal goal) {
    return Math.abs(ball.getX() - goal.getX()) < goal.getGoalDimension().getDepth() / 2f
        && Math.abs(ball.getY() - goal.getY()) < goal.getGoalDimension().getWidth() / 2f;
  }

  @Override
  public boolean test(final I input) {
    return input.getBalls().stream()
        .anyMatch(ball ->
            input.getGoals().stream()
                .anyMatch(goal -> this.isBallInGoal(ball, goal)));
  }
}
