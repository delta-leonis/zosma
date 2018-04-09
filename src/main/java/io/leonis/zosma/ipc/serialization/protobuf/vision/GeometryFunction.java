package io.leonis.zosma.ipc.serialization.protobuf.vision;

import io.leonis.algieba.geometry.Vectors;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.serialization.protobuf.vision.GeometryFunction.GeometryFrame;
import io.reactivex.functions.Function;
import java.util.stream.Collectors;
import lombok.Value;
import org.robocup.ssl.Geometry.GeometryData;

/**
 * The Class GeometryFunction.
 *
 * This class represents a {@link Function} of {@link GeometryData} to {@link GeometryFrame}.
 *
 * @author Rimon Oz
 */
public class GeometryFunction implements Function<GeometryData, GeometryFrame> {
  @Override
  public GeometryFrame apply(final GeometryData geometryData) {
    return
            new GeometryFrame(
                new Field.State(
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
                        .collect(Collectors.toSet())),
                new GoalDimension.State(
                    Vectors.columnVector(
                        geometryData.getField().getGoalWidth(),
                        geometryData.getField().getGoalDepth())));
  }

  @Value
  public static class GeometryFrame {
    private final Field field;
    private final GoalDimension goalDimension;
  }
}
