package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.TerminalPosition;
import org.ssh.torch.view.component.NotificationComponent;
import org.ssh.torch.view.model.NotificationViewModel;

import java.util.Arrays;

/**
 * @author jeroen.dejong
 * @since 12/02/2017.
 */
public class NotificationWindow extends BasicModal {
    private final NotificationViewModel notification;

    public NotificationWindow(NotificationViewModel notification) {
        super(notification.getTitle());
        this.setHints(Arrays.asList(Hint.MODAL, Hint.FIXED_POSITION));
        this.setPosition(TerminalPosition.TOP_LEFT_CORNER);
        this.setComponent(new NotificationComponent(notification));
        this.notification = notification;
    }

    public boolean isVisible(){
        return this.notification.isUnRead();
    }
}
