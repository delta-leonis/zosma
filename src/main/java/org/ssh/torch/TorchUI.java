package org.ssh.torch;

import com.googlecode.lanterna.screen.Screen;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.event.TorchEventPublisher;
import org.ssh.torch.event.notification.Notification;
import org.ssh.torch.event.notification.NotificationPublisher;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.workspace.MainWorkspace;

import java.util.List;

/**
 * @author jeroen.dejong
 * @since 01/02/2017.
 */
public class TorchUI {
    private static final NotificationPublisher notificationPublisher = new NotificationPublisher();
    // TODO Eventbus and fix all dependencies and stuff
    private static final TorchEventPublisher torchEventPublisher = new TorchEventPublisher();
    private static final TorchImpl INSTANCE = createTorch();
    private TorchUI() { }

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

    public static TorchImpl lit() {
        return TorchUI.INSTANCE;
    }

    public static WorkspaceManager addWorkspace(Workspace workspace){
        return TorchUI.lit().addWorkspace(workspace);
    }

    public static Workspace getActiveWorkspace() {
        return TorchUI.lit().getActiveWorkspace();
    }

    public static WorkspaceManager setActiveWorkspace(Workspace workspace){
        return TorchUI.lit().setActiveWorkspace(workspace);
    }

    public static void notify(Notification notification){
        notificationPublisher.addNotification(notification);
    }

    public static void dispatchTorchEvent(TorchEvent event) {
        torchEventPublisher.addTorchEvent(event);
    }

    public static List<Workspace> getWorkspaces() {
        return TorchUI.lit().getWorkspaces();
    }

    public static Screen getScreen() {
        return TorchUI.lit().getScreen();
    }

    public static void main(String[] args) {
        TorchUI.lit(); // light them spul
    }
}
