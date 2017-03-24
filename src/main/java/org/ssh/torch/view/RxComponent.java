package org.ssh.torch.view;

import com.googlecode.lanterna.gui2.Component;
import org.reactivestreams.Subscriber;

/**
 * @author jeroen.dejong
 * @since 08/12/2016.
 */
public interface RxComponent<O> extends Subscriber<O>, Component {
}
