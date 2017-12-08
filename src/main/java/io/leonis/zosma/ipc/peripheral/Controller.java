package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.Identity;
import java.util.*;

/**
 * The Interface Controller.
 *
 * This interface describes the functionality of a controller which maps an entity's identifier
 * to the controls provided by the underlying implementation.
 *
 * @param <I> The type of identifier used to denote the controlled entity.
 * @param <S> The type of controls provided by the controller.
 * @author Jeroen de Jong
 */
public interface Controller<I extends Identity, S> {
  /**
   * @return The identifier to which this controller links its controls.
   */
  I getIdentifier();

  /**
   * @return The controls provided by the controller.
   */
  S getControls();

  /**
   * Supplies a mapping of {@link Controller} to {@link Identity.Supplier}.
   * 
   * @param <C> The type of {@link Controller} being supplied.
   * @param <A> The type of {@link Identity.Supplier} being supplied.
   */
  interface MappingSupplier<C extends Controller, A extends Identity.Supplier> {
    Map<C, Set<A>> getAgentMapping();
  }
}
