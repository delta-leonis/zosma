package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.gui2.Component;
import java.util.Arrays;
import org.ssh.torch.view.BasicWindow;

/**
 * The Class BasicModal.
 *
 * @author Jeroen de Jong
 */
public class BasicModal extends BasicWindow {

  /**
   * Instantiates a new Basic modal.
   *
   * @param title the title
   */
  public BasicModal(String title) {
    this(title, null);
  }

  /**
   * Instantiates a new Basic modal.
   *
   * @param title     the title
   * @param component the component
   */
  public BasicModal(String title, Component component) {
    super(title);
    this.setHints(Arrays.asList(Hint.MODAL, Hint.CENTERED));
    this.setCloseWindowWithEscape(true);
    this.setComponent(component);
  }
}
