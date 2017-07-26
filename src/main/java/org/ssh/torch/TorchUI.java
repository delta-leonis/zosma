package org.ssh.torch;

import com.googlecode.lanterna.screen.Screen;
import java.util.List;
import org.ssh.ipc.event.notification.*;
import org.ssh.torch.view.*;

/**
 * The Class TorchUI.
 *
 * @author Jeroen de Jong
 */
public class TorchUI {

  private static final NotificationPublisher notificationPublisher = new NotificationPublisher();
  private static final TorchImpl INSTANCE = createTorch();

  private TorchUI() {
  }

  private static TorchImpl createTorch() {
    final TorchImpl torch = new TorchImpl();
    final MainWorkspace workspace = new MainWorkspace(torch.getScreen());
    torch.setActiveWorkspace(workspace);

//        // subscribe the notification window
//        Flux.from(notificationPublisher)
//            .map(NotificationViewModel::new)
//            .subscribe(torch.getNotificationWindow());
    return torch;
  }

  /**
   * Add workspace workspace manager.
   *
   * @param workspace the workspace
   * @return the workspace manager
   */
  public static WorkspaceManager addWorkspace(final Workspace workspace) {
    return TorchUI.lit().addWorkspace(workspace);
  }

  /**
   * Lit torch.
   *
   * @return the torch
   */
  public static TorchImpl lit() {
    return TorchUI.INSTANCE;
  }

  /**
   * Gets active workspace.
   *
   * @return the active workspace
   */
  public static Workspace getActiveWorkspace() {
    return TorchUI.lit().getActiveWorkspace();
  }

  /**
   * Sets active workspace.
   *
   * @param workspace the workspace
   * @return the active workspace
   */
  public static WorkspaceManager setActiveWorkspace(final Workspace workspace) {
    return TorchUI.lit().setActiveWorkspace(workspace);
  }

  /**
   * Notify.
   *
   * @param notification the notification
   */
  public static void notify(final Notification notification) {
    notificationPublisher.addNotification(notification);
  }

  /**
   * Gets workspaces.
   *
   * @return the workspaces
   */
  public static List<Workspace> getWorkspaces() {
    return TorchUI.lit().getWorkspaces();
  }

  /**
   * Gets screen.
   *
   * @return the screen
   */
  public static Screen getScreen() {
    return TorchUI.lit().getScreen();
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    TorchUI.lit(); // light them spul
  }
}
