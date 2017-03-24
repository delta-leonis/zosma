package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Panel;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import org.ssh.torch.view.AbstractWorkspace;
import org.ssh.torch.view.Workspace;
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
  public WorkspaceList(Collection<Class<? extends AbstractWorkspace>> workspaceClasses,
      Consumer<WorkspaceClassViewModel<?>> callback) {
    this.withBorder(Borders.singleLine("Workspace selector"));
    workspaceClasses.stream()
        .map(
            (Function<Class<? extends Workspace>, ? extends WorkspaceClassViewModel<? extends Workspace>>) WorkspaceClassViewModel::new)
        .filter(WorkspaceClassViewModel::isConstructable)
        .reduce(new ActionListBox(),
            (list, workspace) -> list
                .addItem(workspace.toString(), () -> callback.accept(workspace)),
            (u, t) -> t)
        .addTo(this);
  }
}
