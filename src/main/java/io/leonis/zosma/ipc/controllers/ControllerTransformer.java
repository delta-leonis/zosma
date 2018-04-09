package io.leonis.zosma.ipc.controllers;

import com.studiohartman.jamepad.ControllerIndex;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.leonis.zosma.game.data.PlayerCommand;
import io.leonis.zosma.math.AveragePlayerCommand;
import io.reactivex.functions.BiFunction;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * The Class ControllerTransformer.
 *
 * Transforms a {@link Set Set&gt;ControllerIndex&lt;} to a {@link PlayerCommand} grouped by its
 * applicable identity.
 *
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class ControllerTransformer
    implements BiFunction<Set<ControllerIndex>, Map<PlayerIdentity, Set<Integer>>, Map<PlayerIdentity, PlayerCommand>> {

  /**
   * Function that maps controller to the desired output type (a command for example).
   */
  private final java.util.function.Function<ControllerIndex, PlayerCommand> outputFunction;

  @Override
  public Map<PlayerIdentity, PlayerCommand> apply(final Set<ControllerIndex> controllerStates,
      final Map<PlayerIdentity, Set<Integer>> controllerMapping) {
    return controllerMapping.entrySet().stream()
        .filter(entry -> controllerStates.stream().map(ControllerIndex::getIndex).anyMatch(entry.getValue()::contains))
        .collect(Collectors.toMap(
            Entry::getKey,
            entry -> new AveragePlayerCommand(
                controllerStates.stream()
                .filter(controller -> entry.getValue().contains(controller.getIndex()))
                .map(outputFunction)
                .collect(Collectors.toSet()))));
  }
}
