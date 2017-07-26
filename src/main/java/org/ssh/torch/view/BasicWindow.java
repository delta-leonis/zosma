package org.ssh.torch.view;

/**
 * Used with custom actions from the Window Interface
 */
public class BasicWindow extends com.googlecode.lanterna.gui2.BasicWindow implements Window {

  /**
   * Instantiates a new Basic window.
   */
  public BasicWindow() {
    this("");
  }

  /**
   * Instantiates a new Basic window.
   *
   * @param title the title
   */
  public BasicWindow(final String title) {
    super(title);
  }

  @Override
  public String toString() {
    return this.getTitle();
  }

  /**
   * Gets workspace.
   *
   * @return the workspace
   */
  public Workspace getWorkspace() {
    return (Workspace) this.getTextGUI();
  }
}
