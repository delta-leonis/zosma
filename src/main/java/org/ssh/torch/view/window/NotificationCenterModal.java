package org.ssh.torch.view.window;

import com.googlecode.lanterna.gui2.*;
import java.util.*;
import java.util.logging.Level;
import org.reactivestreams.*;
import org.ssh.ipc.event.notification.Notification;
import org.ssh.torch.view.component.NotificationComponent;
import org.ssh.torch.view.model.NotificationViewModel;
import org.ssh.torch.view.window.modal.*;

/**
 * The Class NotificationCenterModal.
 *
 * @author Jeroen de Jong
 */
public class NotificationCenterModal extends BasicModal
    implements Subscriber<NotificationViewModel> {

  private final Panel root = new Panel();

  /**
   * Instantiates a new Notification center modal.
   */
  public NotificationCenterModal() {
    super("Notifications");
    this.root.setLayoutManager(new GridLayout(1));
    this.setComponent(this.root);
    this.close(); // hide by default
  }

  /**
   * Open notification center modal.
   *
   * @return the notification center modal
   */
  public NotificationCenterModal open() {
    this.setVisible(true);
    final Set<Hint> hintSet = new HashSet<>(this.getHints());
    hintSet.remove(Hint.NO_FOCUS);
    this.setHints(Collections.unmodifiableSet(hintSet));
    return this;
  }

  @Override
  public void draw(final TextGUIGraphics graphics) {
    super.draw(graphics);
    this.root.getChildren().stream()
        // find notification components
        .filter(component -> component instanceof NotificationComponent)
        // which should be invisible
        .filter(component -> !((NotificationComponent) component).isVisible())
        // and remove those
        .forEach(this.root::removeComponent);
  }

  @Override
  public void close() {
    this.setVisible(false);
    final Set<Hint> hintSet = new HashSet<>(this.getHints());
    hintSet.add(Hint.NO_FOCUS);
    this.setHints(Collections.unmodifiableSet(hintSet));
  }

  @Override
  public void onSubscribe(final Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final NotificationViewModel notificationPresenter) {
    this.root.addComponent(
        new NotificationComponent(
            notificationPresenter));
    this.getWorkspace().setActiveWindow(new NotificationWindow(notificationPresenter));
  }

  @Override
  public void onError(final Throwable t) {
    this.onNext(
        new NotificationViewModel(
            new Notification(
                Level.WARNING,
                t.getMessage(),
                Notification::read)));
  }

  @Override
  public void onComplete() {

  }
}
