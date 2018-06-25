package io.leonis.zosma.ipc.protobuf.vision;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function;
import java.util.stream.Collectors;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * The Class GeometryFunction.
 *
 * This class represents a {@link Function} of {@link GeometryData} to {@link Field}.
 *
 * @author Rimon Oz
 */
public class GeometryFunction implements Function<GeometryData, Field> {

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
            .collect(Collectors.toSet()));
  }
}
