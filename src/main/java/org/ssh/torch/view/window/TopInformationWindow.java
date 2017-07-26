package org.ssh.torch.view.window;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.TorchEvent;
import org.ssh.ipc.event.torch.*;
import org.ssh.torch.view.Workspace;

/**
 * Defines the window at the top of each {@link Workspace}
 * @author Thomas Hakkers
 */
@Slf4j
public class TopInformationWindow extends org.ssh.torch.view.BasicWindow {

  private Label currentWorkspaceLabel;

  /**
   * Instantiates a new Top information window.
   */
  public TopInformationWindow() {
    super("");
    this.setHints(
        Arrays.asList(Hint.NO_DECORATIONS, Hint.NO_POST_RENDERING, Hint.FIXED_POSITION,
            Hint.FIXED_SIZE, Hint.NO_FOCUS));

    currentWorkspaceLabel = new Label("Loading...");

    setComponent(new Panel()
        .addComponent(currentWorkspaceLabel)
        .setLayoutManager(new GridLayout(3)));

    this.setSize(new TerminalSize(80, 2));
    this.setPosition(new TerminalPosition(0, 0));

    Zosma.listen(TerminalEvent.class)
        .filter(TorchEvent::isResized)
        .map(TorchEvent::getSource)
        .subscribe(this::updateSize);

    Zosma.listen(WorkspaceEvent.class)
        .filter(TorchEvent::isSwitched)
        .map(TorchEvent::getSource)
        .map(Workspace::getTitle)
        .subscribe(this.currentWorkspaceLabel::setText);
  }

  private void updateSize(final Terminal terminal) {
    try {
      // Update size of this window
      this.setSize(terminal.getTerminalSize().withRows(2));
    } catch (final Exception exception) {
    }
  }

  @Override
  public void setTextGUI(final WindowBasedTextGUI textGUI) {
    super.setTextGUI(textGUI);
    currentWorkspaceLabel.setText(this.getWorkspace().getTitle());
  }
}
