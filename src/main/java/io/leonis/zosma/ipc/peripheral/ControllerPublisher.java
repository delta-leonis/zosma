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
 * @param <I> The type of identifier used to identify the controller.
 * @param <J> The type of identifier used to identify the entities which are being controlled.
 * @param <C> The type of {@link Controller}.
 * @author Jeroen de Jong
 */
public interface ControllerPublisher<
    I extends Identity,
    J extends Identity,
    C extends Controller<I, ?>>
    extends Publisher<Controller.MappingSupplier<C, J>> {

  /**
   * @return Mapping of applicable agents per controller identifier
   */
  Map<I, Set<J>> getControllerMapping();
}
