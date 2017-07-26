package org.ssh.ipc.event.notification;

import java.util.function.Consumer;
import java.util.logging.Level;
import lombok.*;
import lombok.experimental.NonFinal;

/**
 * The Class Notification.
 *
 * This class describes a notification.
 *
 * @author Jeroen de Jong
 */
@Value
@AllArgsConstructor
@NonFinal
public class Notification {

  Level level;
  String message;
  Consumer<Notification> acceptAction;
  Consumer<Notification> dismissAction;
  @NonFinal
  private boolean read;

  /**
   * Instantiates a new Notification.
   *
   * @param level         the level
   * @param message       the message
   * @param dismissAction the dismiss action
   */
  public Notification(final Level level, final String message, final Consumer<Notification> dismissAction) {
    this(level, message, null, dismissAction, false);
  }

  /**
   * Determines whether the notification has been read.
   *
   * @return True if unread, false otherwise.
   */
  public Boolean isUnRead() {
    return !this.isRead();
  }

  /**
   * Reads the notification.
   */
  public void read() {
    this.read = true;
  }
}
