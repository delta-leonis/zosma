package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author jeroen.dejong
 * @since 12/01/2017.
 */
public class ConstructorList extends Panel {
    public ConstructorList(
            Collection<ConstructorViewModel> constructors,
            Consumer<ConstructorViewModel> callback
    ) {
        if(!constructors.stream().allMatch(ConstructorViewModel::allParameterNamePresent))
            this.addComponent(new Label("Could not infer parameter names.\n" +
                                        "Please pass '-parameters' to the compiler"));
        constructors.stream()
            .reduce(new ActionListBox(),
                    (list, ctor) -> list.addItem(ctor.toString(), () -> callback.accept(ctor)),
                    (u, t) -> t)
            .addTo(this);
    }
}
