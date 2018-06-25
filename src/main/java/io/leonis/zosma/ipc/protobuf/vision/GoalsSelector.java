package io.leonis.zosma.ipc.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.FieldHalf.*;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.BiFunction;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * @author jeroen.dejong.
 */
public class GoalsSelector implements BiFunction<GeometryData, Allegiance, AllegianceTuple<Goal>> {
  private final GoalDimensionSelector goalDimensionSelector = new GoalDimensionSelector();

  @Override
  public AllegianceTuple<Goal> apply(final GeometryData geometry, final Allegiance positiveTeamAllegiance) {
    // TODO Muxify
    final GoalDimension dimension = goalDimensionSelector.apply(geometry);
    final FieldHalf allyFieldHalf = positiveTeamAllegiance.equals(ALLY) ? POSITIVE : NEGATIVE;

    return new AllegianceTuple<>(
        new Goal.State(dimension, allyFieldHalf, ALLY, geometry.getField().getFieldLength()),
        new Goal.State(dimension, allyFieldHalf.opposite(), OPPONENT, geometry.getField().getFieldLength()));
  }
}
