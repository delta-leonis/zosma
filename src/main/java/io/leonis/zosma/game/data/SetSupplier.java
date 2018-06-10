package io.leonis.zosma.game.data;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

/**
 * @author Jeroen de Jong
 * TODO rename
 */
public interface SetSupplier<T> extends Supplier<Set<T>> {
  default Set<T> all() {
    return ImmutableSet.<T>builder().addAll(this.getOpponent()).addAll(getAlly()).build();
  }
}
