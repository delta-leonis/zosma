package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.*;
import java.util.Collection;
import java.util.function.*;
import org.ssh.torch.view.*;
import org.ssh.torch.view.model.reflect.WorkspaceClassViewModel;

/**
 * The Class WorkspaceList.
 *
 * @author Jeroen de Jong
 */
public class WorkspaceList extends Panel {

  /**
   * Instantiates a new Workspace list.
   *
   * @param workspaceClasses the workspace classes
   * @param callback         the callback
   */
  public WorkspaceList(final Collection<Class<? extends AbstractWorkspace>> workspaceClasses,
      final Consumer<WorkspaceClassViewModel<?>> callback) {
    this.withBorder(Borders.singleLine("Workspace selector"));
    workspaceClasses.stream()
        .map(
            (Function<Class<? extends Workspace>, ? extends WorkspaceClassViewModel<? extends Workspace>>) WorkspaceClassViewModel::new)
        .filter(WorkspaceClassViewModel::isConstructible)
        .reduce(new ActionListBox(),
            (list, workspace) -> list
                .addItem(workspace.toString(), () -> callback.accept(workspace)),
            (u, t) -> t)
        .addTo(this);
  }
}
