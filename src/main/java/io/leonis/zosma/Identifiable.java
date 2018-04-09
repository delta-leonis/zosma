package io.leonis.zosma;

/**
 * The Interface Identifiable.
 *
 * This interface describes the functionality of an object which can be identified.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public interface Identifiable {
  /**
   * @return The identity of the supplier.
   */
  Object getIdentity();
}
