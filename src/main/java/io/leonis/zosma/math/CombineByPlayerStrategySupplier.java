package io.leonis.zosma.math;

import io.leonis.zosma.game.data.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import lombok.experimental.Delegate;

/**
 * The Class CombineByPlayerStrategySupplier.
 *
 * This class represents the computation of combining two {@link Strategy.Supplier} by
 * {@link io.leonis.zosma.game.data.Player.PlayerIdentity}.
 *
 * @author Rimon Oz
 */
public class CombineByPlayerStrategySupplier implements Strategy.Supplier {
  @Delegate
  private final Strategy.Supplier strategy;

  /**
   * Computes the {@link Strategy.Supplier} as a result of combining the supplied strategies by
   * {@link io.leonis.zosma.game.data.Player.PlayerIdentity}.
   *
   * @param combinator The {@link BinaryOperator combinator} function.
   * @param leftMap    The first strategy to combine.
   * @param rightMaps  The remaining strategies to combine.
   */
  public CombineByPlayerStrategySupplier(
      final BinaryOperator<PlayerCommand> combinator,
      final Strategy.Supplier leftMap,
      final Collection<Strategy.Supplier> rightMaps
  ) {
    this.strategy =
        rightMaps.stream()
            .reduce(leftMap,
                (left, right) ->
                    () -> left.getStrategy().entrySet().stream()
                        .collect(Collectors.toMap(
                            Entry::getKey,
                            entry ->
                                right.getStrategy().containsKey(entry.getKey())
                                    ? combinator.apply(right.getStrategy().get(entry.getKey()),
                                    entry.getValue())
                                    : entry.getValue())));
  }

  /**
   * Computes the {@link Strategy.Supplier} as a result of combining the supplied strategies by
   * {@link io.leonis.zosma.game.data.Player.PlayerIdentity}.
   *
   * @param combinator The {@link BinaryOperator combinator} function.
   * @param leftMap    The first strategy to combine.
   * @param rightMaps  The remaining strategies to combine.
   */
  public CombineByPlayerStrategySupplier(
      final BinaryOperator<PlayerCommand> combinator,
      final Strategy.Supplier leftMap,
      final Strategy.Supplier... rightMaps
  ) {
    this(combinator, leftMap, Arrays.asList(rightMaps));
  }
}
