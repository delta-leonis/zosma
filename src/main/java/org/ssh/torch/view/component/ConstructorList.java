package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.*;
import java.util.Collection;
import java.util.function.Consumer;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ConstructorList.
 *
 * @author Jeroen de Jong
 */
public class ConstructorList extends Panel {
  /**
   * Instantiates a new Constructor list.
   *
   * @param constructors the constructors
   * @param callback     the callback
   */
  public ConstructorList(
      final Collection<ConstructorViewModel> constructors,
      final Consumer<ConstructorViewModel> callback
  ) {
    if (!constructors.stream().allMatch(ConstructorViewModel::allParameterNamePresent)) {
      this.addComponent(new Label("Could not infer parameter names.\n" +
          "Please pass '-parameters' to the compiler"));
    }
    constructors.stream()
        .reduce(new ActionListBox(),
            (list, ctor) -> list.addItem(ctor.toString(), () -> callback.accept(ctor)),
            (u, t) -> t)
        .addTo(this);
  }
}
