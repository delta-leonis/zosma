package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.RamMeasurements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class RamInfoPublisher.
 *
 * This class represents a {@link org.reactivestreams.Publisher} of {@link
 * RamMeasurements RAM measurements}.
 *
 * @author Jeroen de Jong
 */
public class RamInfoPublisher extends SystemInfoPublisher<RamMeasurements> {

  @Override
  public void subscribe(final Subscriber<? super RamMeasurements> subscriber) {
    Flux.intervalMillis(INTERVAL)
        .subscribeOn(Schedulers.single())
        .map(tick -> RamMeasurements.of(this.hal.getMemory()))
        .distinctUntilChanged()
        .subscribe(subscriber);
  }
}
