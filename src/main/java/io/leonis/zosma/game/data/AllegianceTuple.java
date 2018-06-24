package io.leonis.zosma.game.data;

import lombok.Value;

/**
 * @author jeroen.dejong.
 */
@Value
public class AllegianceTuple<T> {
  T ally, opponent;
}
