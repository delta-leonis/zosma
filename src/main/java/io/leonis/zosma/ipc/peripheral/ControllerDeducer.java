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
public final class ControllerDeducer<
    T extends Controller.SetSupplier<C> & Controller.MapSupplier<I>,
    C extends Controller, I extends Identity, O>
    implements Deducer<T, Map<I, O>> {
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
      final Publisher<T> containerPublisher
  ) {
    return Flux.from(containerPublisher)
        .map(container -> container.getControllerMapping().entrySet().stream()
            .filter(entry -> container.getControllerSet().stream()
                .map(Supplier::getIdentity).anyMatch(entry.getValue()::contains))
            .collect(Collectors.toMap(
                Entry::getKey,
                entry -> outputReducer.apply(
                    container.getControllerSet().stream()
                        .filter(obj -> entry.getValue().contains(obj.getIdentity()))
                      .map(outputFunction)
                      .collect(Collectors.toSet())))))
        .filter(mapping -> !mapping.isEmpty());
  }
}
