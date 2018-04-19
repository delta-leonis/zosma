package io.leonis.zosma.game.engine;

import io.leonis.subra.protocol.Robot;
import io.leonis.subra.protocol.Robot.Measurements;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.reactivex.functions.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * The Class RobotMeasurementsFunction.
 *
 * A {@link Function} which converts {@link Robot.Measurements} to {@link PlayerMeasurements}.
 *
 * @author Rimon Oz
 */
@AllArgsConstructor
public final class RobotMeasurementsFunction
    implements Function<Robot.Measurements, PlayerMeasurements> {

  @Override
  public PlayerMeasurements apply(final Robot.Measurements measurements) {
    return new PlayerMeasurements.State(
        new PlayerIdentity(measurements.getRobotId(), Allegiance.ALLY),
        measurements.getMeasurementsList().stream()
            .collect(Collectors.toMap(
                Measurements.Single::getLabel,
                measurement ->
                    measurement.getValue() * Math.pow(10, measurement.getTenFoldMultiplier()))));
  }
}
