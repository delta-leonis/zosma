package org.ssh.torch.view.model;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.event.notification.Notification;

/**
 * The Class NotificationViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
public class NotificationViewModel implements ViewModel<Notification> {

  @Delegate
  private Notification object;

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return this.getLevel().getName();
  }

  /**
   * Gets label.
   *
   * @return the label
   */
  public Label getLabel() {
    return new Label(this.getMessage());
  }

  /**
   * Gets buttons.
   *
   * @return the buttons
   */
  public List<Button> getButtons() {
    return Stream.of(getAcceptButton(), getDismissButton())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  private Optional<Button> getAcceptButton() {
    return Optional.ofNullable(this.getAcceptAction())
        .map(action -> new Button("Accept", () -> action.accept(this.getObject())));
  }

  private Optional<Button> getDismissButton() {
    return Optional.of(
        new Button("Dismiss", () -> this.getDismissAction().accept(this.getObject()))
    );
  }
}
