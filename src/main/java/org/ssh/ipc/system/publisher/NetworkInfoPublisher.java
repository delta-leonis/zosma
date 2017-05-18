package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.NetworkMeasurements;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class NetworkInfoPublisher.
 *
 * This class represents a {@link org.reactivestreams.Publisher} of {@link NetworkMeasurements
 * network measurements}.
 *
 * @author Rimon Oz
 */
public class NetworkInfoPublisher extends SystemInfoPublisher<NetworkMeasurements> {
  @Override
  public void subscribe(final Subscriber<? super NetworkMeasurements> subscriber) {
    Flux.intervalMillis(INTERVAL)
        .subscribeOn(Schedulers.single())
        .flatMap(tick -> Flux.fromArray(this.hal.getNetworkIFs()))
        .map(NetworkMeasurements::of)
        .distinctUntilChanged()
        .subscribe(subscriber);
  }
}
