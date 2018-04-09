package io.leonis.zosma.game.engine;

import io.leonis.subra.protocol.Robot;
import io.leonis.subra.protocol.Robot.Measurements;
import io.leonis.zosma.exception.UnsafeFunction;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.*;
import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.Value;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class RobotMeasurementsDeducer.
 *
 * This class represents a {@link Deducer} which converts {@link DatagramPacket} to
 * {@link Robot.Measurements}.
 *
 * @author Rimon Oz
 */
@Value
public class RobotMeasurementsDeducer implements Deducer<DatagramPacket, PlayerMeasurements.Supplier> {
  private final TeamColor color;

  @Override
  public Publisher<PlayerMeasurements.Supplier> apply(
      final Publisher<DatagramPacket> datagramPacketPublisher
  ) {
    return Flux.from(datagramPacketPublisher)
        .map(datagramPacket ->
            Arrays.copyOfRange(datagramPacket.getData(), 0, datagramPacket.getLength()))
        .map(new UnsafeFunction<>(Robot.Measurements::parseFrom))
        // get rid of empty measurements
        .filter(measurementsList -> !measurementsList.getMeasurementsList().isEmpty())
        // and put the measurements in a map
        .map(measurements ->
            () -> new PlayerMeasurements.State(
                new PlayerIdentity(measurements.getRobotId(), this.color),
                measurements.getMeasurementsList().stream()
                    .collect(Collectors.toMap(
                        Measurements.Single::getLabel,
                        measurement ->
                            measurement.getValue()
                                * Math.pow(10, measurement.getTenFoldMultiplier())))));
  }
}
