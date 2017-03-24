package org.ssh.torch;

import org.ssh.torch.lifecycle.PreRequisiteManager;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author jeroen.dejong
 * @since 05/02/2017.
 */
public interface Lifecycle extends PreRequisiteManager {
    default void start(){
        Flux.fromIterable(this.getPreRequisites())
            .subscribeOn(Schedulers.single())
            .subscribe(prerequisite -> {
                this.publish(prerequisite);
                prerequisite.run();
            });
    }

    default void stop(){
        // TODO think about life choices
    }
}
