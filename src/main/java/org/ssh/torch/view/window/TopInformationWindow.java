package org.ssh.torch.view.window;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.extern.slf4j.Slf4j;
import org.ssh.torch.TorchAction;
import org.ssh.torch.TorchScope;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.view.Workspace;

import java.util.Arrays;

/**
 * Created by thoma on 22-2-17.
 */
@Slf4j
public class TopInformationWindow extends org.ssh.torch.view.BasicWindow {

    private Label currentWorkspaceLabel;

    public TopInformationWindow() {
        super("");
        this.setHints(Arrays.asList(Hint.NO_DECORATIONS, Hint.NO_POST_RENDERING, Hint.FIXED_POSITION, Hint.FIXED_SIZE, Hint.NO_FOCUS));

        currentWorkspaceLabel = new Label("Loading...");

        setComponent(new Panel()
                .addComponent(currentWorkspaceLabel)
                .setLayoutManager(new GridLayout(3))
            );

        this.setSize(new TerminalSize(80, 2));
        this.setPosition(new TerminalPosition(0,0));

        this.<Terminal>addEventListener(TorchScope.TERMINAL, TorchAction.RESIZE, (terminal) -> {
            try {
                // Update size of this window
                this.setSize(terminal.getTerminalSize().withRows(2));
            } catch(Exception exception) { }
        });

        this.<Workspace>addEventListener(TorchScope.WORKSPACE, TorchAction.SWITCH, (workspace) ->
            this.currentWorkspaceLabel.setText(workspace.getTitle())
        );
    }

    @Override
    public void setTextGUI(WindowBasedTextGUI textGUI) {
        super.setTextGUI(textGUI);
        currentWorkspaceLabel.setText(this.getWorkspace().getTitle());
    }
}
