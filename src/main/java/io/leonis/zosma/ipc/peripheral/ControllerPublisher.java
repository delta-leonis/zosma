package io.leonis.zosma.ipc.peripheral;

import com.studiohartman.jamepad.ControllerIndex;
import io.leonis.zosma.game.Identity;
import io.leonis.zosma.ipc.peripheral.Controller.ControllerIdentity;
import io.leonis.zosma.ipc.peripheral.ControllerPublisher.Frame;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import lombok.*;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * The Class ControllerPublisher.
 *
 * Publishes an container with implements {@link Controller.MapSupplier} and
 * {@link Controller.SetSupplier}. This allows dynamic rebinding of controllers on runtime.
 *
 * TODO determine if interval should be configurable in this wrapper class.
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class ControllerPublisher<I extends Identity, C extends Controller>
    implements Publisher<Frame<C, I>> {

  /**
   * Publisher of a set of connected controllers.
   */
  private final Publisher<Controller.SetSupplier<C>> setSupplierPublisher;

  /**
   * Publisher of the mapping of controllable to set of controllers.
   */
  private final Publisher<Controller.MapSupplier<I>> mapSupplierPublisher;

  /**
   * Creates an ControllerPublisher that allows for dynamic rebinding based on the latest map
   * published on the provided {@code mapPublisher}.
   *
   * @param amount       The amount of controllers to listen for.
   * @param interval     The poll rate.
   * @param adapter      The adapter from jamepad to an controller object.
   * @param mapPublisher Publisher of the assignment map.
   */
  public ControllerPublisher(
      final int amount,
      final Duration interval,
      final Function<ControllerIndex, C> adapter,
      final Publisher<Controller.MapSupplier<I>> mapPublisher
  ){
    this(new ControllerSetPublisher<>(interval, adapter, amount), mapPublisher);
  }

  /**
   * Creates an ControllerPublisher with a static mapping.
   *
   * @param amount   The amount of controllers to listen for.
   * @param interval The poll rate.
   * @param adapter  The adapter from jamepad to an controller object.
   * @param mapping  The assignment mapping.
   */
  public ControllerPublisher(
      final int amount,
      final Duration interval,
      final Function<ControllerIndex, C> adapter,
      final Map<I, Set<ControllerIdentity>> mapping
  ){
    this(new ControllerSetPublisher<>(interval, adapter, amount),
         Flux.just(() -> mapping));
  }

  @Override
  public void subscribe(final Subscriber<? super Frame<C, I>> s) {
    Flux.combineLatest(setSupplierPublisher, mapSupplierPublisher, Frame::new).subscribe(s);
  }

  @Value
  static class Frame<C extends Controller, I extends Identity>
      implements Controller.SetSupplier<C>, Controller.MapSupplier<I> {
    @lombok.experimental.Delegate
    Controller.SetSupplier<C> set;
    @lombok.experimental.Delegate
    Controller.MapSupplier<I> map;
  }
}
