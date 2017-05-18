package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.DiskMeasurements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class DiskInfoPublisher.
 *
 * This class represents a {@link org.reactivestreams.Publisher} of {@link DiskMeasurements disk
 * measurements}.
 *
 * @author Rimon Oz
 */
public class DiskInfoPublisher extends SystemInfoPublisher<DiskMeasurements> {
  @Override
  public void subscribe(final Subscriber<? super DiskMeasurements> subscriber) {
    Flux.intervalMillis(INTERVAL)
        .subscribeOn(Schedulers.single())
        .flatMap(tick -> Flux.fromArray(this.hal.getDiskStores()))
        .map(DiskMeasurements::of)
        .distinctUntilChanged()
        .subscribe(subscriber);
  }
}
