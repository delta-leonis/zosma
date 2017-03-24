package org.ssh.benchmarks;

import java.util.Set;
import org.reactivestreams.Subscriber;

/**
 * The Interface Profiler.
 *
 * This interface describes a profiler, that is a collection of probes which are used
 * in a single testing setup.
 *
 * @param <U> The type of measurements made by this profiler.
 * @param <M> The type of value measured by the probes inside this profiler.
 * @author Rimon Oz
 */
public interface Profiler<U extends DescriptiveMeasurement<M>, M> extends Subscriber<U> {

  /**
   * Returns the probes inside this profiler.
   *
   * @return The probes inside this profiler.
   */
  Set<Probe> getProbes();

  /**
   * Applies the profiler with the supplied {@link Probe}.
   *
   * @param <T>   The type of data flowing into the {@link Probe}.
   * @param probe The probe to apply.
   * @return The probe itself, modified using the {@link Profiler}.
   */
  <T> Probe<T, U, M> with(Probe<T, U, M> probe);
}
