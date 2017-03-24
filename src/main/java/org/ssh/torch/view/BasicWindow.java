package org.ssh.torch.view;

import org.ssh.torch.event.TorchEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Used with custom actions from the Window Interface
 */
public class BasicWindow extends com.googlecode.lanterna.gui2.BasicWindow implements Window {
    private List<Consumer<TorchEvent<?>>> eventListeners = new CopyOnWriteArrayList<>();
    public BasicWindow() {
        this("");
    }

    public BasicWindow(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public Workspace getWorkspace(){
        return (Workspace) this.getTextGUI();
    }

    @Override
    public Window addEventListener(Consumer<TorchEvent<?>> listener) {
        eventListeners.add(listener);
        return this;
    }

    @Override
    public List<Consumer<TorchEvent<?>>> getEventListeners() {
        return Collections.unmodifiableList(this.eventListeners);
    }
}
