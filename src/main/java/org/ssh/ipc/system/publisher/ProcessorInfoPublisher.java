package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.ProcessorMeasurements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class ProcessorInfoPublisher.
 *
 * This class describes a {@link org.reactivestreams.Publisher} of {@link ProcessorMeasurements
 * processor measurements}.
 *
 * @author Jeroen de Jong
 */
public class ProcessorInfoPublisher extends SystemInfoPublisher<ProcessorMeasurements> {

  @Override
  public void subscribe(final Subscriber<? super ProcessorMeasurements> subscriber) {
    Flux.intervalMillis(INTERVAL)
        .subscribeOn(Schedulers.single())
        .map(tick -> this.hal.getProcessor())
        .map(ProcessorMeasurements::of)
        .distinctUntilChanged()
        .subscribe(subscriber);
  }
}
