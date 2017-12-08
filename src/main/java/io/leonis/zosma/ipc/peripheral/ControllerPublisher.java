package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.Identity;
import java.util.*;
import org.reactivestreams.Publisher;

/**
 * The Interface ControllerPublisher.
 *
 * This interface describes the functionality of a {@link Publisher} which publishes mappings of
 * {@link Controller} to the entities they control.
 *
 * @param <I> The type of identifier used to identify the controlled entities.
 * @param <C> The type of {@link Controller}.
 * @param <A> The type of entity which is being controlled.
 * @author Jeroen de Jong
 */
public interface ControllerPublisher<
    I extends Identity,
    C extends Controller<I, ?>,
    A extends Identity.Supplier>
    extends Publisher<Controller.MappingSupplier<C, A>> {

  /**
   * @return Mapping of applicable agents per controller identifier
   */
  Map<I, Set<A>> getControllerMapping();
}
