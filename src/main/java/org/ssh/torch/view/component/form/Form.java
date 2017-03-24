package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Form extends Panel {
    private final Panel labels;
    private final Panel elements;
    private final Panel buttons;

    private Form() {
        super();
        this.labels   = new Panel();
        this.buttons  = new Panel();
        this.elements = new Panel();
        this.setLayoutManager(new BorderLayout());
        this.addComponent(labels,   BorderLayout.Location.LEFT);
        this.addComponent(elements, BorderLayout.Location.RIGHT);
        this.addComponent(buttons,  BorderLayout.Location.BOTTOM);
    }

    public static Form of(Map<String, FormElement<?>> elements, Map<String, Consumer<List<FormElement<?>>>> buttons) {
        Form form = new Form();
        // create buttons
        buttons.entrySet().stream()
            .map(entry -> new Button(entry.getKey(), () -> entry.getValue().accept(form.getFormData())))
            .forEach(form::addComponent);
        // add form elements
        elements.entrySet().forEach(entry -> form.addComponent(entry.getKey(), entry.getValue()));
        return form;
    }

    public synchronized Form addComponent(Button button) {
        this.buttons.addComponent(button);
        return this;
    }

    public synchronized Form addComponent(String label, FormElement<?> element) {
        this.labels.addComponent(new Label(label));
        this.elements.addComponent(element);
        return this;
    }

    public synchronized List<FormElement<?>> getFormData(){
        return this.elements.getChildren().stream()
                .filter(child -> child instanceof FormElement)
                .map(child -> (FormElement<?>) child)
                .collect(Collectors.toList());
    }
}
