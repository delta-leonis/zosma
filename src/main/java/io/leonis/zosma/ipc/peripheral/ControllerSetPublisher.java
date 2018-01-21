package io.leonis.zosma.ipc.peripheral;

import com.studiohartman.jamepad.*;
import java.time.Duration;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.*;
import lombok.Value;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * The Class ControllerSetPublisher.
 *
 * Publishes all connected controllers at a provided interval.
 *
 * @param <C> Identity of {@link Controller}.
 * @author Jeroen de Jong
 */
@Value
public class ControllerSetPublisher<C extends Controller>
    implements Publisher<Controller.SetSupplier<C>> {

  /**
   * Duration between polls.
   */
  private final Duration interval;
  /**
   * Adapter used to transform jamepad state.
   */
  private final Function<ControllerIndex, C> adapter;
  /**
   * Jamepad backend for managing controllers
   */
  private final ControllerManager manager;

  /**
   * Constructs ControllerSetPublisher and initializes native extensions.
   *
   * @param interval Duration between polls.
   * @param adapter Adapter used to transform jamepad state.
   * @param amount Amount of controllers to listen for.
   */
  public ControllerSetPublisher(
      final Duration interval,
      final Function<ControllerIndex, C> adapter,
      final int amount
  ) {
    this.manager = new ControllerManager(amount);
    this.manager.initSDLGamepad();
    this.interval = interval;
    this.adapter = adapter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribe(final Subscriber<? super Controller.SetSupplier<C>> subscriber) {
    Flux.interval(interval)
        .map(tick ->
            // Loop all controllers
            IntStream.range(0, manager.getNumControllers())
                // grab the states
                .mapToObj(manager::getControllerIndex)
                // ignore any disconnected controller
                .filter(ControllerIndex::isConnected)
                // map to custom state and collect as a set.
                .map(adapter)
                .collect(Collectors.toSet()))
        .map(Frame::new)
        .subscribe(subscriber);
  }

  /**
   * The Class Frame.
   *
   * @author Jeroen de Jong
   */
  @Value
  private class Frame implements Controller.SetSupplier<C> {
    /**
     * The {@link Set} of {@link Controller controllers} captured in this frame.
     */
    private Set<C> controllerSet;
  }
}
