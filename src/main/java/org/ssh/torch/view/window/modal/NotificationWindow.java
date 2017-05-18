package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.TerminalPosition;
import java.util.Arrays;
import org.ssh.torch.view.component.NotificationComponent;
import org.ssh.torch.view.model.NotificationViewModel;

/**
 * The Class NotificationWindow.
 *
 * @author Jeroen de Jong
 */
public class NotificationWindow extends BasicModal {

  private final NotificationViewModel notification;

  /**
   * Instantiates a new Notification window.
   *
   * @param notification the notification
   */
  public NotificationWindow(final NotificationViewModel notification) {
    super(notification.getTitle());
    this.setHints(Arrays.asList(Hint.MODAL, Hint.FIXED_POSITION));
    this.setPosition(TerminalPosition.TOP_LEFT_CORNER);
    this.setComponent(new NotificationComponent(notification));
    this.notification = notification;
  }

  public boolean isVisible() {
    return this.notification.isUnRead();
  }
}
