package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.FieldHalf.*;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * The Class GoalAdapter.
 *
 * Adapts a {@link GeometryData} into one {@link Goal} per allegiance.
 *
 * @author Jeroen de Jong
 */
public class GoalAdapter implements BiFunction<GeometryData, Allegiance, AllegianceTuple<Goal>> {
  private final GoalDimensionAdapter goalDimensionAdapter = new GoalDimensionAdapter();

  @Override
  public AllegianceTuple<Goal> apply(final GeometryData geometry, final Allegiance positiveTeamAllegiance) {
    final GoalDimension dimension = goalDimensionAdapter.apply(geometry);
    final FieldHalf allyFieldHalf = positiveTeamAllegiance.equals(ALLY) ? POSITIVE : NEGATIVE;

    return new AllegianceTuple<>(
        new Goal.State(dimension, allyFieldHalf, ALLY, geometry.getField().getFieldLength()),
        new Goal.State(dimension, allyFieldHalf.opposite(), OPPONENT, geometry.getField().getFieldLength()));
  }
}
