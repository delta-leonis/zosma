package io.leonis.zosma.game;

/**
 * The Interface Identity.
 *
 * This interface describes the functionality of an object which can be identified.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public interface Identity {
  /**
   * Supplies an {@link Identity}.
   */
  interface Supplier {
    /**
     * @return The identity of the supplier.
     */
    Identity getIdentity();
  }
}
