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

  public FieldHalf opposite() {
    return this.equals(POSITIVE) ? NEGATIVE : POSITIVE;
  }
}
