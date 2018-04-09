package io.leonis.zosma.ipc.controllers;

import static java.util.stream.Collectors.*;

import com.studiohartman.jamepad.ControllerIndex;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import io.reactivex.Flowable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.reactivestreams.*;

/**
 * The Class ReassigningControllerPublisher.
 *
 * A class that allows for dynamic rebinding of a controller mapping based on controller input. This
 * class needs a mapping of controllers and a set of controller inputs and returns a new mapping of
 * controllers. The remapping is done based on the provided {@link BiFunction}.
 *
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class ReassigningControllerPublisher implements
    Publisher<Map<PlayerIdentity, Set<Integer>>> {

  private final Map<PlayerIdentity, Set<Integer>> originalMapping;
  private final Flowable<Set<ControllerIndex>> controllerSetPublisher;
  private final BiFunction<Map<PlayerIdentity, Set<Integer>>, ControllerIndex, Map<PlayerIdentity, Set<Integer>>> remapper;

  @Override
  public void subscribe(final Subscriber<? super Map<PlayerIdentity, Set<Integer>>> subscriber) {
    this.controllerSetPublisher
        .map(controllers ->
            controllers.stream()
                .reduce(this.originalMapping, this.remapper,
                    (leftMap, rightMap) ->
                        Stream.concat(leftMap.entrySet().stream(), rightMap.entrySet().stream())
                            .flatMap(entry -> entry.getValue().stream().map(
                                controller -> new SimpleImmutableEntry<>(entry.getKey(),
                                    controller)))
                            .collect(groupingBy(Entry::getKey, mapping(Entry::getValue, toSet())))))
        .subscribe(subscriber);
  }
}
