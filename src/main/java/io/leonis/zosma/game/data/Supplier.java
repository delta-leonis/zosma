package io.leonis.zosma.game.data;

/**
 * @author jeroen.dejong.
 */
public interface Supplier<T> {
  T getAlly();
  T getOpponent();
}
