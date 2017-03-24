package org.ssh.torch.view.window;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import java.util.Arrays;
import org.ssh.torch.view.Workspace;

/**
 * The Class InformationWindow.
 *
 * @author Jeroen de Jong
 */
public class InformationWindow extends BasicWindow {

  private final Label label;

  /**
   * Instantiates a new Information window.
   *
   * @param workspace the workspace
   */
  public InformationWindow(Workspace workspace) {
    this.label = new Label("");
    this.label.setText(workspace.getTitle());
    this.setPosition(getCenterPosition(workspace.getScreen().getTerminalSize()));
    this.setComponent(label);
    this.setHints(Arrays.asList(Hint.NO_FOCUS, Hint.FIXED_POSITION, Hint.NO_DECORATIONS));
  }

  private TerminalPosition getCenterPosition(TerminalSize totalSize) {
    int screenCenter = totalSize.getColumns() / 2;
    int labelCenter = this.label.getText().length() / 2;
    return new TerminalPosition(screenCenter - labelCenter, 0);
  }
}
