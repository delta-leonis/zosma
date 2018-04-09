package io.leonis.zosma.game.data;

/**
 * The Enum FieldHalf.
 *
 * Identifies a field half in a standard SSL match.
 *
 * @author Jeroen de Jong
 */
public enum FieldHalf {
  POSITIVE, NEGATIVE;

  /**
   * The Interface Supplier.
   *
   * Supplies a {@link FieldHalf}.
   *
   * @author Jeroen de Jong
   */
  interface Supplier {

    /**
     * @return a {@link FieldHalf}.
     */
    FieldHalf getFieldHalf();
  }
}
