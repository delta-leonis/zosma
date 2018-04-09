package io.leonis.zosma.ipc.controllers;

import com.studiohartman.jamepad.*;
import io.reactivex.Flowable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.*;
import org.reactivestreams.*;

/**
 * The Class ControllerSetPublisher.
 *
 * Publishes all connected controllers at a provided interval.
 *
 * @author Jeroen de Jong
 */
public final class ControllerSetPublisher implements Publisher<Set<ControllerState>> {

  /**
   * Interval between polls in ms.
   */
  private final int interval;

  /**
   * Jamepad controller manager.
   */
  private final ControllerManager manager;

  /**
   * @param amount   amount of controllers to listen for.
   * @param interval milliseconds in between polls.
   */
  public ControllerSetPublisher(
      final int amount,
      final int interval
  ) {
    this.manager = new ControllerManager(amount);
    this.interval = interval;
  }

  @Override
  public void subscribe(final Subscriber<? super Set<ControllerState>> subscriber) {
    Flowable.interval(this.interval, TimeUnit.MILLISECONDS)
        .doOnSubscribe(tick -> manager.initSDLGamepad())
        .doOnComplete(manager::quitSDLGamepad)
        .doOnNext(tick -> manager.update())
        .map(tick -> IntStream.range(0, manager.getNumControllers())
            .mapToObj(manager::getState)
            .collect(Collectors.toSet()))
        .subscribe(subscriber);
  }
}
