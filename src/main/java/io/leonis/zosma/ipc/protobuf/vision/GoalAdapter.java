package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.FieldHalf.*;
import static io.leonis.zosma.game.data.TeamColor.*;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function3;
import org.robocup.ssl.Geometry.GeometryData;
import org.robocup.ssl.Referee.SSL_Referee;

/**
 * The Class GoalAdapter.
 *
 * Adapts a {@link GeometryData} into one {@link Goal} per allegiance.
 *
 * @author Jeroen de Jong
 */
public class GoalAdapter implements
    Function3<GeometryData, SSL_Referee, AllegianceTuple<Team>, AllegianceTuple<Goal>> {

  private final GoalDimensionAdapter goalDimensionAdapter = new GoalDimensionAdapter();

  @Override
  public AllegianceTuple<Goal> apply(
      final GeometryData geometry,
      final SSL_Referee referee,
      final AllegianceTuple<Team> teams
  ) {
    final GoalDimension dimension = goalDimensionAdapter.apply(geometry);
    final TeamColor positiveTeamColor = referee.getBlueTeamOnPositiveHalf() ? BLUE : YELLOW;
    final FieldHalf allyFieldHalf =
        positiveTeamColor.equals(teams.getAlly().getTeamColor()) ? POSITIVE : NEGATIVE;

    return new AllegianceTuple<>(
        new Goal.State(dimension, allyFieldHalf, ALLY, geometry.getField().getFieldLength()),
        new Goal.State(dimension, allyFieldHalf.opposite(), OPPONENT,
            geometry.getField().getFieldLength()));
  }
}
