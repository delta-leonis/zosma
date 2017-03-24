package org.ssh.torch.event;

import java.util.List;
import java.util.function.Consumer;
import org.ssh.torch.TorchAction;
import org.ssh.torch.TorchScope;

/**
 * The Interface EventHandler.
 *
 * This interface describes the functionality of an event handler.
 *
 * @param <T> The type of events to handle.
 * @author Jeroen de Jong
 */
public interface EventHandler<T> {

  /**
   * Adds an event listener for the supplied scope and on the supplied action.
   *
   * @param <C>      The type of event accepted by the listener.
   * @param scope    The scope of the event.
   * @param action   The event action.
   * @param listener The event listener.
   * @return The event handler itself.
   */
  default <C> T addEventListener(TorchScope scope, TorchAction action, Consumer<C> listener) {
    return addEventListener(event -> {
      if (scope.equals(event.getScope()) && action.equals(event.getAction())) {
        listener.accept(event.getContext());
      }
    });
  }

  /**
   * Adds an event listener to the event handler.
   *
   * @param listener The event listener to add.
   * @return The event handler itself.
   */
  T addEventListener(Consumer<TorchEvent<?>> listener);

  /**
   * Processes the supplied event.
   *
   * @param event The event to process.
   * @return The event handler itself.
   */
  default T process(TorchEvent<?> event) {
    getEventListeners().forEach(listener -> listener.accept(event));
    return (T) this;
  }

  /**
   * Returns the event listeners attached to this handler.
   *
   * @return The event listeners attached to this handler.
   */
  List<Consumer<TorchEvent<?>>> getEventListeners();
}
