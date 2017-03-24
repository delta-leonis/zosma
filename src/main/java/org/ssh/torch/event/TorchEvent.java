package org.ssh.torch.event;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.Getter;
import lombok.Value;
import org.ssh.torch.TorchAction;
import org.ssh.torch.TorchScope;
import org.ssh.torch.view.Workspace;

/**
 * The Class TorchEvent.
 *
 * This class describes an event within Torch.
 *
 * @param <T> The type of context.
 * @author Thomas Hakkers
 */
@Value
public class TorchEvent<T> {
    @Getter
    private TorchScope scope;

    private T context;

    @Getter
    private TorchAction action;

    /**
     * Returns the context.
     *
     * @param <O> The type of the context.
     * @return    The context.
     */
    public <O> O getContext(){
        return (O)context;
    }

    /**
     * Creates an event for {@link Window}.
     *
     * @param window the window
     * @param action the action
     * @return the torch event
     */
    public static TorchEvent<Window> of(Window window, TorchAction action) {
        return new TorchEvent<>(TorchScope.WINDOW, window, action);
    }

    /**
     * Creates an event for {@link Component}.
     *
     * @param window the window
     * @param action the action
     * @return the torch event
     */
    public static TorchEvent<Component> of(Component window, TorchAction action) {
        return new TorchEvent<>(TorchScope.COMPONENT, window, action);
    }

    /**
     * Creates an event for {@link Workspace}.
     *
     * @param window the window
     * @param action the action
     * @return the torch event
     */
    public static TorchEvent<Workspace> of(Workspace window, TorchAction action) {
        return new TorchEvent<>(TorchScope.WORKSPACE, window, action);
    }

    /**
     * Creates an event for {@link Terminal}.
     *
     * @param window the window
     * @param action the action
     * @return the torch event
     */
    public static TorchEvent<Terminal> of(Terminal window, TorchAction action) {
        return new TorchEvent<>(TorchScope.TERMINAL, window, action);
    }
}
