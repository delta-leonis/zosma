package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.Identity;
import io.leonis.zosma.game.Identity.Supplier;
import io.leonis.zosma.game.engine.Deducer;
import io.leonis.zosma.ipc.peripheral.Controller.ControllerIdentity;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class ControllerDeducer.
 *
 * Deduces a {@link Set} of {@link Controller Controllers} to {@code O} to a {@link Map} grouped
 * by it's applicable identity.
 *
 * @param <C> Type of controller state
 * @param <I> Type of assignee identity
 * @param <O> Type of output
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class ControllerDeducer<C extends Controller, I extends Identity, O>
    implements Deducer<Controller.SetSupplier<C>, Map<I, O>> {

  /**
   * Mapping of the applicable set of Controller identities per identity.
   */
  private final Map<I, Set<ControllerIdentity>> assignmentMap;

  /**
   * Function that maps controller to the desired output type.
   */
  private final Function<C, O> outputFunction;

  /**
   * Function which reduces the accumulated set of O to O.
   */
  private final Function<Set<O>, O> outputReducer;

  /**
   * {@inheritDoc}
   */
  @Override
  public Publisher<Map<I, O>> apply(
      final Publisher<Controller.SetSupplier<C>> setSupplierPublisher
  ) {
    return Flux.from(setSupplierPublisher)
        .map(Controller.SetSupplier::getControllerSet)
        .map(set -> assignmentMap.entrySet().stream()
            .filter(entry -> set.stream()
                .map(Supplier::getIdentity).anyMatch(entry.getValue()::contains))
            .collect(Collectors.toMap(
                Entry::getKey,
                entry -> outputReducer.apply(
                    set.stream()
                        .filter(obj -> entry.getValue().contains(obj.getIdentity()))
                      .map(outputFunction)
                      .collect(Collectors.toSet())))))
        .filter(mapping -> !mapping.isEmpty());
  }
}
