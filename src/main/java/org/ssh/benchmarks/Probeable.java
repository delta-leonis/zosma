package org.ssh.benchmarks;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import reactor.core.Disposable;
import reactor.core.publisher.TopicProcessor;

/**
 * The Interface Probeable
 *
 * This interface describes the functionality of an object which can be probed.
 *
 * @author Jeroen de Jong
 */
public interface Probeable {

  /**
   * @return A {@link Set} containing identifiers of all active probes
   */
  default Set<String> getProbes() {
    return this.getProbeProcessors().keySet();
  }

  /**
   * todo Not part of public API, make private once JDK-8071453
   * @return The set containing al probes.
   */
  Map<String, TopicProcessor> getProbeProcessors();

  /**
   * Start listening to a probe as identified by the supplied identifier.
   * @param identifier Unique identifier of the specified probe.
   * @param consumer   Consumer of {@code <M>}.
   * @param <V>        Value held by the {@link DescriptiveMeasurement}.
   * @param <M>        The type of {@link DescriptiveMeasurement}.
   * @return The probeable itself.
   * @throws NoSuchElementException If no probe exists with the supplied identifier.
   */
  default <V, M extends DescriptiveMeasurement<V>> Disposable probe(
      String identifier,
      Consumer<M> consumer
  ) {
    return this.getProbeProcessors().get(identifier).subscribe(consumer);
  }

  /**
   * Creates a probe point which acts as a receiver for the data. Will put the received data in
   * a {@link SimpleMeasurement}.
   * todo make private once JDK-8071453
   * @param identifier Unique identifier of the probe.
   * @param <V>        Type of value.
   * @return A probe point which emits {@link SimpleMeasurement SimpleMeasurements} to a probe.
   */
  default <V> Consumer<V> createProbeTarget(
      String identifier) {
    return this.createProbeTarget(identifier, value -> new SimpleMeasurement<>(value, identifier));
  }

  /**
   * Creates a probe point which acts as a receiver for the data. Will put the received data in a
   * {@link DescriptiveMeasurement} created by the supplied {@code measurementConstructor}.
   * todo Not part of public API, make private once JDK-8071453
   * @param identifier             Unique identifier for the probe.
   * @param measurementConstructor Function to create a {@link DescriptiveMeasurement} from {@code
   *                               <V>}.
   * @param <V>                    Type of value which will be received by the probe point.
   * @param <M>                    Type of Measurement which will be emitted.
   * @return A probe point which emits {@code <M>} to a probe
   */
  default <V, M extends DescriptiveMeasurement> Consumer<V> createProbeTarget(
      String identifier,
      Function<V, M> measurementConstructor
  ) {
    this.getProbeProcessors().put(identifier, TopicProcessor.create());
    return value ->
        this.getProbeProcessors().get(identifier).onNext(measurementConstructor.apply(value));
  }
}
