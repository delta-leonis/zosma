package org.ssh.torch.view;

import com.googlecode.lanterna.gui2.Component;
import org.reactivestreams.Subscriber;

/**
 * The Interface RxComponent.
 *
 * @param <O> the type parameter
 * @author Jeroen de Jong
 */
public interface RxComponent<O> extends Subscriber<O>, Component {

}
