package io.leonis.zosma.ipc.protobuf.vision;

import io.leonis.algieba.geometry.Vectors;
import io.leonis.zosma.game.data.GoalDimension;
import io.reactivex.functions.Function;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * The Class GoalDimensionAdapter.
 *
 * Adapts a {@link GeometryData} into a common {@link GoalDimension} for all goals.
 *
 * @author Jeroen de Jong
 */
public final class GoalDimensionAdapter implements Function<GeometryData, GoalDimension> {

  @Override
  public GoalDimension apply(final GeometryData geometryData) {
    return new GoalDimension.State(
        Vectors.columnVector(
            geometryData.getField().getGoalWidth(),
            geometryData.getField().getGoalDepth()));
  }
}
