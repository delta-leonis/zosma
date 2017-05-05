package org.ssh.torch;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.screen.VirtualScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.TorchEvent;
import org.ssh.ipc.event.TorchEvent.Action;
import org.ssh.ipc.event.torch.TerminalEvent;
import org.ssh.ipc.event.torch.WorkspaceEvent;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.window.NotificationCenterModal;

/**
 * The Class TorchImpl.
 *
 * @author Jeroen de Jong
 */
public final class TorchImpl implements WorkspaceManager {

  /**
   * The {@link com.googlecode.lanterna.terminal.Terminal terminal object}.
   */
  private final Terminal terminal;
  /**
   * The {@link com.googlecode.lanterna.screen.Screen screen object}.
   */
  @Getter
  private final VirtualScreen screen;
  private final List<Workspace> workspaces;
  /**
   * The {@link com.googlecode.lanterna.gui2.MultiWindowTextGUI activeWorkspace}.
   */
  @Getter
  private Workspace activeWorkspace;
  // TODO this should be deleted
  private NotificationCenterModal notificationWindow = new NotificationCenterModal();

  /**
   * Constructs a new window manager.
   */
  public TorchImpl() {
    try {
      TerminalFactory factory = new DefaultTerminalFactory();
      this.workspaces = new CopyOnWriteArrayList<>();
      this.terminal = factory.createTerminal();
      this.screen = new VirtualScreen(new TerminalScreen(this.terminal));
      this.screen.startScreen();
      this.terminal.addResizeListener((terminal, newSize) ->
          Zosma.broadcast(new TerminalEvent(Action.RESIZED, this.terminal)));
    } catch (Exception exception) {
      throw new RuntimeException("Houston....", exception);
    }

    Zosma.listen(WorkspaceEvent.class)
        .filter(WorkspaceEvent::isCreated)
        .map(TorchEvent::getSource)
        .subscribe(this::setActiveWorkspace);
  }

  @Override
  public WorkspaceManager setActiveWorkspace(Workspace workspace) {
    if (this.activeWorkspace == workspace) {
      return this;
    }
    if (!workspaces.contains(workspace)) {
      this.addWorkspace(workspace);
    }
    if (this.activeWorkspace != null) {
      this.activeWorkspace.getWorkspaceThread().pause();
    }
    this.activeWorkspace = workspace;
    workspace.getWorkspaceThread().resume();
    Zosma.broadcast(new WorkspaceEvent(Action.SWITCHED, this.activeWorkspace));
    return this;
  }

  @Override
  public List<Workspace> getWorkspaces() {
    return Collections.unmodifiableList(this.workspaces);
  }

  @Override
  public WorkspaceManager addWorkspace(Workspace workspace) {
    this.workspaces.add(workspace);
    return this;
  }
}
