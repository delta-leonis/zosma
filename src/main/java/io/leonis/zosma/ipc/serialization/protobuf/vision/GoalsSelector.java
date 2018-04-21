package io.leonis.zosma.ipc.serialization.protobuf.vision;

import static io.leonis.zosma.game.data.Allegiance.*;
import static io.leonis.zosma.game.data.FieldHalf.*;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Goal.Goals;
import io.reactivex.functions.BiFunction;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * @author jeroen.dejong.
 */
public class GoalsSelector implements BiFunction<GeometryData, Allegiance, Goals> {
  private final GoalDimensionSelector goalDimensionSelector = new GoalDimensionSelector();

  @Override
  public Goals apply(final GeometryData geometry, final Allegiance positiveTeamAllegiance)
      throws Exception {
    // TODO Muxify
    GoalDimension dimension = goalDimensionSelector.apply(geometry);
    FieldHalf allyFieldHalf = positiveTeamAllegiance.equals(ALLY) ? POSITIVE : NEGATIVE;

    return new Goals(
        new Goal.State(dimension, allyFieldHalf, ALLY, geometry.getField().getFieldLength()),
        new Goal.State(dimension, allyFieldHalf.opposite(), OPPONENT, geometry.getField().getFieldLength()));
  }
}
