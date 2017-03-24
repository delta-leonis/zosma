package org.ssh.torch.view.model;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.event.notification.Notification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jeroen.dejong
 * @since 05/02/2017.
 */
@Value
public class NotificationViewModel implements ViewModel<Notification> {
    @Delegate
    private Notification object;

    public String getTitle(){
        return this.getLevel().getName();
    }

    public Label getLabel() {
        return new Label(this.getMessage());
    }

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
