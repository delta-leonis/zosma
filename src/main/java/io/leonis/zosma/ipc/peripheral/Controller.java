package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.Identity;
import java.util.*;

/**
 * The Interface Controller.
 *
 * This interface describes the functionality of a controller which maps an entity's identifier
 * to the controls provided by the underlying implementation.
 *
 * @param <I> The type of identifier used to identify the controller
 * @param <S> The type of controls provided by the controller.
 * @author Jeroen de Jong
 */
public interface Controller<I extends Identity, S> extends Identity.Supplier {
  /**
   * @return The identifier of the controller.
   */
  I getIdentity();

  /**
   * @return The controls provided by the controller.
   */
  S getControls();

  /**
   * Supplies a mapping of {@link Controller} to {@link Identity} of objects it is controlling.
   *
   * @param <C> The type of {@link Controller} being supplied.
   * @param <A> The type of {@link Identity} of the object it is controlling.
   */
  interface MappingSupplier<C extends Controller, A extends Identity> {
    Map<C, Set<A>> getAgentMapping();
  }
}
