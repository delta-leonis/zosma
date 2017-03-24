package org.ssh.ipc.system.publisher;

import org.reactivestreams.Subscriber;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.resource.ProcessorInfo;
import org.ssh.ipc.system.resource.state.ProcessorInfoState;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Class ProcessorInfoPublisher.
 *
 * This class describes a {@link org.reactivestreams.Publisher} of {@link ProcessorInfo}.
 *
 * @author Jeroen de Jong
 */
public class ProcessorInfoPublisher extends SystemInfoPublisher<ProcessorInfo> {
    @Override
    public void subscribe(Subscriber<? super ProcessorInfo> subscriber) {
        Flux.intervalMillis(this.INTERVAL)
                .subscribeOn(Schedulers.single())
                .map(tick -> this.hal.getProcessor())
                .map(ProcessorInfoState::of)
                .distinctUntilChanged()
                .subscribe(subscriber);
    }
}
