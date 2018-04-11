package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import java.util.stream.Collectors;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * @author jeroen.dejong.
 */
public class FieldSelector implements Function<GeometryData, Field> {

  @Override
  public Field apply(final GeometryData geometryData) {
    return new Field.State(
        geometryData.getField().getFieldWidth(),
        geometryData.getField().getFieldLength(),
        geometryData.getField().getFieldLinesList().stream()
            .map(fieldLineSegment ->
                new FieldLine.State(
                    fieldLineSegment.getP1().getX(),
                    fieldLineSegment.getP1().getY(),
                    fieldLineSegment.getP2().getX(),
                    fieldLineSegment.getP2().getY(),
                    fieldLineSegment.getThickness()))
            .collect(Collectors.toSet()),
        geometryData.getField().getFieldArcsList().stream()
            .map(fieldCircularArc ->
                new FieldArc.State(
                    fieldCircularArc.getCenter().getX(),
                    fieldCircularArc.getCenter().getY(),
                    fieldCircularArc.getA1(),
                    fieldCircularArc.getA2(),
                    fieldCircularArc.getThickness(),
                    fieldCircularArc.getRadius()))
            .collect(Collectors.toSet()),
        new GoalDimensionSelector().apply(geometryData));
  }
}
