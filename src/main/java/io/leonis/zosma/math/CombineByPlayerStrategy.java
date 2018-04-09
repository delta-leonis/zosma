package io.leonis.zosma.math;

import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * The Class CombineByPlayerStrategy.
 *
 * This class represents the computation of combining two {@link Strategy} by
 * {@link io.leonis.zosma.game.data.Player.PlayerIdentity}.
 *
 * @author Rimon Oz
 */
@AllArgsConstructor
public class CombineByPlayerStrategy implements Strategy {
  private final BinaryOperator<PlayerCommand> combinator;
  private final Strategy leftMap;
  private final Collection<Strategy> rightMaps;

  /**
   * Computes the {@link Strategy} as a result of combining the supplied strategies by
   * {@link PlayerIdentity}.
   *
   * @param combinator The {@link BinaryOperator combinator} function.
   * @param leftMap The first strategy to combine.
   * @param rightMaps The remaining strategies to combine.
   */
  public CombineByPlayerStrategy(
      final BinaryOperator<PlayerCommand> combinator,
      final Strategy leftMap,
      final Strategy... rightMaps
  ) {
    this(combinator, leftMap, Arrays.asList(rightMaps));
  }

  @Override
  public Map<PlayerIdentity, PlayerCommand> getAssignments() {
    return rightMaps.stream()
        .map(Strategy::getAssignments)
        .reduce(leftMap.getAssignments(),
            (left, right) ->
                left.entrySet().stream()
                    .collect(Collectors.toMap(
                        Entry::getKey,
                        entry ->
                            right.containsKey(entry.getKey())
                                ? combinator.apply(right.get(entry.getKey()), entry.getValue())
                                : entry.getValue())));
  }
}
