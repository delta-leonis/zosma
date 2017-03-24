package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.gui2.Component;
import org.ssh.torch.view.BasicWindow;

import java.util.Arrays;

/**
 * @author jeroen.dejong
 * @since 13/01/2017.
 */
public class BasicModal extends BasicWindow {

    public BasicModal(String title) {
        this(title, null);
    }

    public BasicModal(String title, Component component){
        super(title);
        this.setHints(Arrays.asList(Hint.MODAL, Hint.CENTERED));
        this.setCloseWindowWithEscape(true);
        this.setComponent(component);
    }
}
