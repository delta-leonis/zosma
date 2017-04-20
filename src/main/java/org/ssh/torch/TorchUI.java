package org.ssh.torch;

import com.googlecode.lanterna.screen.Screen;
import java.util.List;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.event.TorchEventPublisher;
import org.ssh.torch.event.notification.Notification;
import org.ssh.torch.event.notification.NotificationPublisher;
import org.ssh.torch.view.MainWorkspace;
import org.ssh.torch.view.Workspace;

/**
 * The Class TorchUI.
 *
 * @author Jeroen de Jong
 */
public class TorchUI {

  private static final NotificationPublisher notificationPublisher = new NotificationPublisher();
  // TODO Eventbus and fix all dependencies and stuff
  private static final TorchEventPublisher torchEventPublisher = new TorchEventPublisher();
  private static final TorchImpl INSTANCE = createTorch();

  private TorchUI() {
  }

  private static TorchImpl createTorch() {
    TorchImpl torch = new TorchImpl();
    MainWorkspace workspace = new MainWorkspace(torch.getScreen());
    torch.setActiveWorkspace(workspace);

//        // subscribe the notification window
//        Flux.from(notificationPublisher)
//            .map(NotificationViewModel::new)
//            .subscribe(torch.getNotificationWindow());
    torchEventPublisher.subscribe(torch);
    return torch;
  }

  /**
   * Add workspace workspace manager.
   *
   * @param workspace the workspace
   * @return the workspace manager
   */
  public static WorkspaceManager addWorkspace(Workspace workspace) {
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
  public static WorkspaceManager setActiveWorkspace(Workspace workspace) {
    return TorchUI.lit().setActiveWorkspace(workspace);
  }

  /**
   * Notify.
   *
   * @param notification the notification
   */
  public static void notify(Notification notification) {
    notificationPublisher.addNotification(notification);
  }

  /**
   * Dispatch torch event.
   *
   * @param event the event
   */
  public static void dispatchTorchEvent(TorchEvent event) {
    torchEventPublisher.addTorchEvent(event);
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
  public static void main(String[] args) {
    TorchUI.lit(); // light them spul
  }
}
