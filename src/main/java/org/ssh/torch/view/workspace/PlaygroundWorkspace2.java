package org.ssh.torch.view.workspace;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;
import org.ssh.torch.view.AbstractWorkspace;
import org.ssh.torch.view.component.form.SimpleFormBuilder;

/**
 * @author jeroen.dejong
 */
public class PlaygroundWorkspace2 extends AbstractWorkspace {

    public PlaygroundWorkspace2() {
        super("Random stuffies space");
    }

    @Override
    protected void construct() {
        Window w = new BasicWindow("Basicwindow");
        w.setComponent(
            new SimpleFormBuilder()
                .addField("floatvalue", 15f)
                .addField("doublevalue", 15d)
                .addField("integervalue", 15 )
                .addField("longvalue", 15L)
                .addField("stringvalue", "lel")
                .build()
        );
        this.setActiveWindow(w);
    }
}
