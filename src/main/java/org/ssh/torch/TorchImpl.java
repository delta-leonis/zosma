package org.ssh.torch;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.screen.VirtualScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import lombok.Getter;
import org.ssh.torch.event.EventSubscriber;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.window.NotificationCenterModal;

/**
 * The Class TorchImpl.
 *
 * @author Jeroen de Jong
 */
public final class TorchImpl implements WorkspaceManager, EventSubscriber<TorchImpl> {

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

  private List<Consumer<TorchEvent<?>>> eventListeners = new CopyOnWriteArrayList<>();

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
          TorchUI.dispatchTorchEvent(TorchEvent.of(this.terminal, TorchAction.RESIZE))
      );
    } catch (Exception exception) {
      throw new RuntimeException("Houston....", exception);
    }

    this.addEventListener(TorchScope.WORKSPACE, TorchAction.CREATED, this::setActiveWorkspace);
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
    TorchUI.dispatchTorchEvent(TorchEvent.of(this.activeWorkspace, TorchAction.SWITCH));
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

  @Override
  public TorchImpl addEventListener(Consumer<TorchEvent<?>> listener) {
    this.eventListeners.add(listener);
    return this;
  }

  @Override
  public List<Consumer<TorchEvent<?>>> getEventListeners() {
    return Collections.unmodifiableList(this.eventListeners);
  }

  @Override
  public void onNext(TorchEvent<?> torchEvent) {
    getEventListeners().forEach(listener -> listener.accept(torchEvent));
    this.getWorkspaces().forEach(workspace -> workspace.process(torchEvent));
  }

  @Override
  public void onComplete() {
    this.onNext(TorchEvent.of(terminal, TorchAction.CLOSED));
  }
}
