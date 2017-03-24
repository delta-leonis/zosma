package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.RamInfo;
import org.ssh.ipc.system.resource.state.RamInfoState;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class RamInfoPublisher.
 *
 * This class describes a {@link org.reactivestreams.Publisher} of {@link
 * org.ssh.ipc.system.resource.RamInfo}*.
 *
 * @author Jeroen de Jong
 */
public class RamInfoPublisher extends SystemInfoPublisher<RamInfo> {

  @Override
  public void subscribe(Subscriber<? super org.ssh.ipc.system.resource.RamInfo> subscriber) {
    Flux.intervalMillis(INTERVAL)
        .subscribeOn(Schedulers.single())
        .map(tick -> RamInfoState.of(Runtime.getRuntime()))
        .distinctUntilChanged()
        .subscribe(subscriber);
  }
}
