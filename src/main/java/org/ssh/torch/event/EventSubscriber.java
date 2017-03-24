package org.ssh.torch.event;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * The Interface EventSubscriber.
 *
 * This interface describes the functionality of a {@link Subscriber} to {@link TorchEvent}.
 *
 * @param <T> The type of event.
 * @author Jeroen de Jong
 */
public interface EventSubscriber<T> extends EventHandler<T>, Subscriber<TorchEvent<?>> {
    @Override
    default void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    default void onError(Throwable t){
        // ignoring may be irresponsible, but i'm gangster like that
    }
}
