package org.ssh.torch.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import lombok.Getter;
import org.ssh.torch.TorchUI;
import org.ssh.torch.WorkspaceThread;
import org.ssh.torch.event.EventHandler;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.event.notification.Notification;
import org.ssh.torch.view.window.TopInformationWindow;
import org.ssh.torch.view.window.modal.WorkspaceSwitchModal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * @author jeroen.dejong
 * @since 01/02/2017.
 */

public abstract class AbstractWorkspace extends MultiWindowTextGUI implements Workspace {
    @Getter
    private final String title;

    private static final Theme THEME = new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.MAGENTA, SGR.BOLD);
    private static Theme resetTheme;
    private List<Consumer<TorchEvent<?>>> eventListeners = new CopyOnWriteArrayList<>();

    public AbstractWorkspace(String title) {
        this(title, TorchUI.getScreen());
    }

    public AbstractWorkspace(String title, Screen screen) {
        super(new WorkspaceThread.Factory(), screen);
        resetTheme = this.getTheme();
        this.title = title.toUpperCase();
        // listener for window switcher
        this.addListener((window, keyStroke) -> {
            System.out.println(keyStroke);
            if(keyStroke.getKeyType().equals(KeyType.Character) && "[]".contains(keyStroke.getCharacter().toString()))
                this.cycleActiveWindow(keyStroke.getCharacter().equals('['));
            return true;
        });
        // listener for workspace wizard
        this.addListener((window, keyStroke) -> {
            if(keyStroke.getKeyType().equals(KeyType.F1))
                this.setActiveWindow(new WorkspaceSwitchModal());
            return true;
        });
        this.addListener((window, keyStroke) -> {
            // TODO Create and/or hide/show the notificationwindow
//            if(keyStroke.getKeyType().equals(KeyType.F12))
//                TorchUI.lit().toggleNotificationWindow();
            return true;
        });
        // Move active window
        this.addListener((window, keyStroke) -> {
            if(keyStroke.isAltDown()) {
                TerminalPosition size = this.getActiveWindow().getPosition();
                if(keyStroke.getKeyType().equals(KeyType.ArrowDown))
                    this.getActiveWindow().setPosition(size.withRelative(new TerminalPosition(0,1)));
                if(keyStroke.getKeyType().equals(KeyType.ArrowUp))
                    this.getActiveWindow().setPosition(size.withRelative(new TerminalPosition(0,-1)));
                if(keyStroke.getKeyType().equals(KeyType.ArrowLeft))
                    this.getActiveWindow().setPosition(size.withRelative(new TerminalPosition(-1,0)));
                if(keyStroke.getKeyType().equals(KeyType.ArrowRight))
                    this.getActiveWindow().setPosition(size.withRelative(new TerminalPosition(1,0)));
            }
            return true;
        });

        this.addListener((window, keyStroke) -> {
            if(keyStroke.getKeyType().equals(KeyType.F10))
                TorchUI.notify(new Notification(Level.INFO, "Test", Notification::read));
            return true;
        });
        // TODO revise, maybe use events or something
        getWorkspaceThread().start(); // initialize thread
        getWorkspaceThread().pause();
        getWorkspaceThread().invokeLater(this::construct);

        // pass all events through to the windows as well
        this.addEventListener(event ->
            this.getWindows().stream()
                    .filter(EventHandler.class::isInstance)
                    .map(EventHandler.class::cast)
                    .forEach(window -> window.process(event)));

        this.addWindow(new TopInformationWindow());
    }

    @Override
    public Workspace addEventListener(Consumer<TorchEvent<?>> listener) {
        this.eventListeners.add(listener);
        return this;
    }

    @Override
    public List<Consumer<TorchEvent<?>>> getEventListeners() {
        return Collections.unmodifiableList(this.eventListeners);
    }

    @Override
    public synchronized WindowBasedTextGUI addWindow(com.googlecode.lanterna.gui2.Window window) {
        WindowBasedTextGUI windowBasedTextGUI = super.addWindow(window);
        window.setPosition(window.getPosition().withRelativeRow(2));
        return windowBasedTextGUI;
    }

    /**
     * Close your eyes... clear your mind... and imagine a world
     *  in which this obscure method actually has useful javadoc
     */
    protected abstract void construct();

    @Override
    public synchronized MultiWindowTextGUI setActiveWindow(com.googlecode.lanterna.gui2.Window activeWindow) {
        if(!this.getWindows().contains(activeWindow))
            this.addWindow(activeWindow);
        super.setActiveWindow(activeWindow);
        return this;
    }
}
