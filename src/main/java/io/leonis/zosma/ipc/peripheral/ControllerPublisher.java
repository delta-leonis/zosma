package io.leonis.zosma.ipc.peripheral;

import com.studiohartman.jamepad.ControllerIndex;
import io.leonis.zosma.game.Identity;
import io.leonis.zosma.ipc.peripheral.Controller.*;
import io.leonis.zosma.ipc.peripheral.ControllerPublisher.Frame;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import lombok.*;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * @author Jeroen de Jong
 */
@Value
@AllArgsConstructor
public class ControllerPublisher<I extends Identity, C extends Controller> implements Publisher<Frame> {
  private final Publisher<Controller.SetSupplier<C>> setSupplierPublisher;
  private final Publisher<Controller.MapSupplier<I>> mapSupplierPublisher;

  public ControllerPublisher(
      final int amount,
      final Duration interval,
      final Function<ControllerIndex, C> adapter,
      final Publisher<Controller.MapSupplier<I>> mapSupplierPublisher
  ){
    this(new ControllerSetPublisher<>(interval, adapter, amount), mapSupplierPublisher);
  }

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
  public void subscribe(final Subscriber<? super Frame> s) {
    Flux.combineLatest(setSupplierPublisher, mapSupplierPublisher, Frame::new)
        .subscribe(s);
  }

  @Value
  class Frame implements Controller.SetSupplier<C>, Controller.MapSupplier<I> {
    @lombok.experimental.Delegate
    Controller.SetSupplier<C> set;
    @lombok.experimental.Delegate
    Controller.MapSupplier<I> map;
  }
}
