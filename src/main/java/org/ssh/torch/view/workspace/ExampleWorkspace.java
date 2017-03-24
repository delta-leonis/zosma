package org.ssh.torch.view.workspace;

import com.googlecode.lanterna.gui2.*;
import org.ssh.torch.Default;
import org.ssh.torch.view.AbstractWorkspace;

import java.net.InetAddress;
import java.util.Collections;

/**
 * @author jeroen.dejong
 * @since 11/01/2017.
 */
public class ExampleWorkspace extends AbstractWorkspace {
    private String ip;
    private int port;

    public ExampleWorkspace(
            @Default("123.123.123.123") String ip,
            @Default("1234") Integer port
    ){
        super("ExampleWorkspace");
        this.ip = ip;
        this.port = port;
    }

    public ExampleWorkspace(
            @Default("123.123.123.123") InetAddress ip,
            @Default("1234") Integer port
    ){
        this(ip.getHostAddress(), port);
    }

    public ExampleWorkspace(){
        this("default_ip", 1234);
    }

    public ExampleWorkspace(Object test){ super("test"); }

    @Override
    protected void construct() {
        Window window = new BasicWindow("Example");
        window.setComponent(
                new Panel()
                        .addComponent(new Label("Created ExampleWorkspace with " + ip + " and " +port))
                        .addComponent(new Button("Close", window::close))
        );
        window.setHints(Collections.singletonList(Window.Hint.CENTERED));
        this.addWindow(window);
    }
}
