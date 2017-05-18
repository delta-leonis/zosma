package org.ssh.torch.view.window.modal;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.ssh.torch.view.component.ConstructorList;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ConstructorModal.
 *
 * @param <C> The type of object produced by the wrapped supplied collection of {@link
 *            ConstructorViewModel}
 * @author Jeroen de Jong
 */
@Slf4j
public class ConstructorModal<C> extends BasicModal {

  /**
   * Instantiates a new constructor modal.
   *
   * @param constructors The constructors to wrap.
   */
  public ConstructorModal(
      final Collection<ConstructorViewModel> constructors,
      final Consumer<C> constructedConsumer
  ) {
    super("Select constructor: ");
    final ConstructorViewModel firstConstructor = constructors.iterator().next();
    if (constructors.size() == 1 && firstConstructor.getParameterCount() == 0) {
      try {
        constructedConsumer.accept(firstConstructor.create(Collections.emptyList()));
        this.close();
      } catch (final IllegalAccessException | InstantiationException | InvocationTargetException exception) {
        log.error("Couldn't instantiate object!", exception);
      }
    } else {
      this.setComponent(
          new ConstructorList(
              constructors,
              constructor ->
                  this.createParameterFormModal(constructor, constructedConsumer)));
    }
  }

  /**
   * Creates a new {@link ParameterFormModal} for the supplied single {@link ConstructorViewModel}
   * and supplied the callback which will be called upon construction.
   *
   * @param constructor         The {@link ConstructorViewModel}.
   * @param constructedConsumer The callback.
   */
  private void createParameterFormModal(
      final ConstructorViewModel constructor,
      final Consumer<C> constructedConsumer
  ) {
    this.getWorkspace()
        .addWindow(new ParameterFormModal<>(constructor, constructedConsumer));
    this.close();
  }
}
