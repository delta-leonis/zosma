package io.leonis.zosma.game.data;

import lombok.Value;

/**
 * The Class AllegianceTuple.
 *
 * A class containing two instances of type {@code T} for each allegiance.
 *
 * @author Jeroen de Jong
 */
@Value
public class AllegianceTuple<T> {
  T ally, opponent;
}
