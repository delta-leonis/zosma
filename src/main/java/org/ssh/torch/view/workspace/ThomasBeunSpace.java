package org.ssh.torch.view.workspace;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.input.KeyType;
import org.ssh.torch.view.AbstractWorkspace;

import java.util.stream.IntStream;

/**
 * Created by jeroen.dejong on 18/02/2017.
 */
public class ThomasBeunSpace extends AbstractWorkspace {
    private Window window;
    private TextBox textBox;
    private int x = 0;
    private int y = 0;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private String emptyTextBox;
    private static final char THOMAS = 'O';

    public ThomasBeunSpace() {
        super("Space van de beunkees");
    }

    @Override
    protected void construct() {

        this.generateEmptyTextBox();

        window = new BasicWindow();
        textBox = new TextBox(new TerminalSize(WIDTH,HEIGHT), "snake");
        textBox.setEnabled(false);

        this.updatePosition();

        window.setComponent(textBox);
        this.addWindow(window);

        this.addListener((window, keyStroke) -> {
            if(keyStroke.getKeyType().equals(KeyType.Character)) {
                if(keyStroke.getCharacter().equals('w'))
                    y -= 1;
                if(keyStroke.getCharacter().equals('a'))
                    x -= 1;
                if(keyStroke.getCharacter().equals('s'))
                    y += 1;
                if(keyStroke.getCharacter().equals('d'))
                    x += 1;

                this.updatePosition();
            }

            return true;
        });
    }

    private void updatePosition(){
        StringBuilder result = new StringBuilder(emptyTextBox);
        result.setCharAt(x + y * (WIDTH+1), THOMAS);
        textBox.setText(result.toString());
    }

    private void generateEmptyTextBox(){
        String row = IntStream.iterate(0, i -> i + 1)
                .limit(WIDTH)
                .mapToObj(i -> " ")
                .reduce("", (a,b) -> a+b).concat("\n");

        emptyTextBox = IntStream.iterate(0, i -> i + 1)
                .limit(HEIGHT)
                .mapToObj(i -> row)
                .reduce("", (a,b) -> a+b);
    }
}
