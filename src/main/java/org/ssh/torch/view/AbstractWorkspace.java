package org.ssh.torch.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.util.logging.Level;
import lombok.Getter;
import org.ssh.ipc.event.notification.Notification;
import org.ssh.torch.TorchUI;
import org.ssh.torch.WorkspaceThread;
import org.ssh.torch.view.window.TopInformationWindow;
import org.ssh.torch.view.window.modal.WorkspaceSwitchModal;

/**
 * The Class AbstractWorkspace.
 *
 * @author Jeroen de Jong
 */
public abstract class AbstractWorkspace extends MultiWindowTextGUI implements Workspace {

  private static final Theme THEME = new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.MAGENTA,
      SGR.BOLD);
  private static Theme resetTheme;
  @Getter
  private final String title;

  /**
   * Instantiates a new Abstract workspace.
   *
   * @param title the title
   */
  public AbstractWorkspace(String title) {
    this(title, TorchUI.getScreen());
  }

  /**
   * Instantiates a new Abstract workspace.
   *
   * @param title  the title
   * @param screen the screen
   */
  public AbstractWorkspace(String title, Screen screen) {
    super(new WorkspaceThread.Factory(), screen);
    resetTheme = this.getTheme();
    this.title = title.toUpperCase();
    // listener for window switcher
    this.addListener((window, keyStroke) -> {
      if (keyStroke.getKeyType().equals(KeyType.Character) && "[]"
          .contains(keyStroke.getCharacter().toString())) {
        this.cycleActiveWindow(keyStroke.getCharacter().equals('['));
      }
      return true;
    });
    // listener for workspace wizard
    this.addListener((window, keyStroke) -> {
      if (keyStroke.getKeyType().equals(KeyType.F1)) {
        this.setActiveWindow(new WorkspaceSwitchModal());
      }
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
      if (keyStroke.isAltDown()) {
        TerminalPosition size = this.getActiveWindow().getPosition();
        if (keyStroke.getKeyType().equals(KeyType.ArrowDown)) {
          this.getActiveWindow()
              .setPosition(size.withRelative(new TerminalPosition(0, 1)));
        }
        if (keyStroke.getKeyType().equals(KeyType.ArrowUp)) {
          this.getActiveWindow()
              .setPosition(size.withRelative(new TerminalPosition(0, -1)));
        }
        if (keyStroke.getKeyType().equals(KeyType.ArrowLeft)) {
          this.getActiveWindow()
              .setPosition(size.withRelative(new TerminalPosition(-1, 0)));
        }
        if (keyStroke.getKeyType().equals(KeyType.ArrowRight)) {
          this.getActiveWindow()
              .setPosition(size.withRelative(new TerminalPosition(1, 0)));
        }
      }
      return true;
    });

    this.addListener((window, keyStroke) -> {
      if (keyStroke.getKeyType().equals(KeyType.F10)) {
        TorchUI.notify(new Notification(Level.INFO, "Test", Notification::read));
      }
      return true;
    });

    getWorkspaceThread().start(); // initialize thread
    getWorkspaceThread().pause();
    getWorkspaceThread().invokeLater(this::construct);

    this.addWindow(new TopInformationWindow());
  }

  @Override
  public synchronized WindowBasedTextGUI addWindow(com.googlecode.lanterna.gui2.Window window) {
    WindowBasedTextGUI windowBasedTextGUI = super.addWindow(window);
    window.setPosition(window.getPosition().withRelativeRow(2));
    return windowBasedTextGUI;
  }

  @Override
  public synchronized MultiWindowTextGUI setActiveWindow(
      com.googlecode.lanterna.gui2.Window activeWindow) {
    if (!this.getWindows().contains(activeWindow)) {
      this.addWindow(activeWindow);
    }
    super.setActiveWindow(activeWindow);
    return this;
  }

  /**
   * Close your eyes... clear your mind... and imagine a world
   * in which this obscure method actually has useful javadoc
   */
  protected abstract void construct();
}
