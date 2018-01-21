package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.Identity;
import java.util.*;
import lombok.Value;

/**
 * The Interface Controller.
 *
 * This interface describes the functionality of a controller which maps an entity's identifier
 * to the controls provided by the underlying implementation.
 *
 * @author Jeroen de Jong
 */
public interface Controller extends Identity.Supplier {

  /**
   * @return The identifier of the controller.
   */
  ControllerIdentity getIdentity();

  /**
   * @return The name of the controller.
   */
  String getName();

  /**
   * The Interface SetSupplier.
   *
   * Supplies a {@link Set} of {@link Controller Controllers}.
   *
   * @param <C> type of controller
   * @author Jeroen de Jong
   */
  interface SetSupplier<C extends Controller> {

    /**
     * @return Set of connected controllers.
     */
    Set<C> getControllerSet();
  }

  /**
   * The Interface MapSupplier.
   *
   * Supplies a {@link Map} of {@link Identity identities} of controllable objects to a {@link Set}
   * of applicable {@link ControllerIdentity ControllerIdentities}.
   *
   * @param <I> identity type of a controllable object.
   */
  interface MapSupplier<I extends Identity> {

    Map<I, Set<ControllerIdentity>> getControllerMapping();
  }

  /**
   * The Class ControllerIdentity.
   *
   * Describes the identity of a controller based on it's internal index.
   * Note that the index is specific to how jamepad works and shouldn't be relied on.
   *
   * @author Jeroen de Jong
   */
  @Value
  class ControllerIdentity implements Identity {
    /**
     * The index of the {@link Controller}.
     */
    private final int index;
  }
}
