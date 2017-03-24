package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import org.ssh.torch.view.model.NotificationViewModel;

/**
 * @author jeroen.dejong
 * @since 05/02/2017.
 */
public class NotificationComponent extends Panel {
    private final NotificationViewModel notification;

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

    public boolean isVisible(){
        return this.notification.isUnRead();
    }
}
