package org.ssh.torch;

import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.LifeCycleEvent;
import org.ssh.torch.lifecycle.PreRequisiteManager;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * The Interface LifeCycle.
 *
 * @author Jeroen de Jong
 */
public interface LifeCycle extends PreRequisiteManager {

  /**
   * Start.
   */
  default void start() {
    Flux.fromIterable(this.getPreRequisites())
        .subscribeOn(Schedulers.single())
        .subscribe(prerequisite -> {
          Zosma.broadcast(
              new LifeCycleEvent<>(
                  prerequisite,
                  this,
                  LifeCycleEvent.State.PREREQUISITE));
          prerequisite.run();
        });
  }

  /**
   * Stop.
   */
  default void stop() {
    // TODO think about life choices
  }
}
