package org.ssh.benchmarks;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * The Interface Probe.
 *
 * This interface describes the functionality of a probe, that is a function
 * which {@link Flux#transform(Function) transforms} a {@link Flux}. In a
 * benchmarking procedure the probe does not affect the {@link Flux} which
 * it is probing.
 *
 * @param <T> The type of data coming into the probe.
 * @param <U> The type of data flowing out of the probe.
 * @param <M> The type of measurement made inside the probe.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Probe<T, U extends DescriptiveMeasurement<M>, M> {
    /**
     * Applies the probe to the {@link Flux}
     *
     * @param probeable The probeable {@link Flux}.
     * @return          The resultant {@link Flux}.
     */
    Flux<U> process(Flux<T> probeable);

    /**
     * Probes the data inside the {@link Flux}.
     *
     * @param probeable The probeable {@link Flux}.
     * @return          The resultant (unaffected) {@link Flux}.
     */
    default Flux<T> probe(Flux<T> probeable) {
        this.process(probeable);
        return probeable;
    }
}
