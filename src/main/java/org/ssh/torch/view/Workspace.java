package org.ssh.torch.view;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import org.ssh.torch.Torch;
import org.ssh.torch.WorkspaceThread;
import org.ssh.torch.event.EventHandler;

import java.util.Optional;

/**
 * @author jeroen.dejong
 * @since 25/01/2017.
 */
public interface Workspace extends WindowBasedTextGUI, EventHandler<Workspace> {
    String getTitle();

    default boolean isConstructible() {
        return Optional.ofNullable(this.getClass().getAnnotation(Torch.class))
                .map(Torch::constructible)
                .orElse(true);
    }

    default WorkspaceThread getWorkspaceThread(){
        return (WorkspaceThread) this.getGUIThread();
    }
}
