package io.leonis.zosma.ipc.controllers;

import static com.studiohartman.jamepad.ControllerButton.DPAD_LEFT;
import static com.studiohartman.jamepad.ControllerButton.DPAD_RIGHT;
import static java.util.stream.Collectors.toCollection;

import com.google.common.collect.ImmutableSet;
import com.studiohartman.jamepad.*;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.reactivex.functions.BiFunction;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * The Class ReassignControllersFunction.
 *
 * Reassigns the controller based on the DPAD. use {@code DPAD_LEFT} to append the controller to the
 * first player with a lower id; use {@code DPAD_RIGHT} to append the controller to the first player
 * with a higher id.
 *
 * @author Jeroen de Jong
 */
public final class ReassignControllersFunction implements
    BiFunction<Map<PlayerIdentity, Set<Integer>>, ControllerIndex, Map<PlayerIdentity, Set<Integer>>> {

  @Override
  public Map<PlayerIdentity, Set<Integer>> apply(
      final Map<PlayerIdentity, Set<Integer>> mapping,
      final ControllerIndex controller
  ) throws Exception {
    final TreeSet<PlayerIdentity> ids = mapping.entrySet().stream()
        .map(Entry::getKey)
        .collect(toCollection(() -> new TreeSet<>(Comparator.comparingInt(PlayerIdentity::getId))));

    return mapping.entrySet().stream()
        .collect(Collectors.toMap(
            Entry::getKey,
            entry -> {
              try {
                if (controller.isButtonJustPressed(DPAD_LEFT)
                    && mapping.get(ids.higher(entry.getKey())).contains(controller.getIndex())
                    || (controller.isButtonJustPressed(DPAD_RIGHT)
                    && mapping.get(ids.lower(entry.getKey())).contains(controller.getIndex()))
                    ) {
                  return ImmutableSet.<Integer>builder()
                      .addAll(entry.getValue())
                      .add(controller.getIndex())
                      .build();
                }
              } catch (ControllerUnpluggedException e) {
                // This is fine because of the methodsignature of BiFunction
                throw new RuntimeException(e);
              }
              return entry.getValue().stream()
                  .filter(a -> a.equals(controller.getIndex()))
                  .collect(Collectors.toSet());
            }));
  }
}
