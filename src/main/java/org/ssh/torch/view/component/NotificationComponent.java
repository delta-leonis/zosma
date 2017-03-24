package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import org.ssh.torch.view.model.NotificationViewModel;

/**
 * The Class NotificationComponent.
 *
 * @author Jeroen de Jong
 */
public class NotificationComponent extends Panel {

  private final NotificationViewModel notification;

  /**
   * Instantiates a new Notification component.
   *
   * @param notification the notification
   */
  public NotificationComponent(NotificationViewModel notification) {
    this.notification = notification;
    this.setLayoutManager(new GridLayout(3));
    // add the label
    this.addComponent(notification.getLabel());
    // add the buttons
    notification.getButtons().forEach(this::addComponent);
    // set the title
    this.withBorder(Borders.doubleLine(notification.getTitle()));
  }

  /**
   * Is visible boolean.
   *
   * @return the boolean
   */
  public boolean isVisible() {
    return this.notification.isUnRead();
  }
}
