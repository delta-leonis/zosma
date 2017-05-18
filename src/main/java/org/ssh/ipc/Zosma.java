package org.ssh.ipc;

import java.util.concurrent.ConcurrentHashMap;
import reactor.core.publisher.*;

/**
 * The Class Zosma.
 *
 * This class contains the functionality of a concurrent event bus with a publish and subscribe
 * mechanism.
 *
 * @author Rimon Oz
 */
public class Zosma {
  /** The mapping of {@link Class} to {@link reactor.core.publisher.TopicProcessor processors}. */
  private static final ConcurrentHashMap<Class<?>, TopicProcessor<? extends Event>> listenerMap
      = new ConcurrentHashMap<>();

  private Zosma() {
    // private constructor to hide the implicit public one.
  }

  /**
   * Broadcasts an {@link Event} if a {@link TopicProcessor processor} is available.
   * @param event The {@link Event} to broadcast.
   * @param <E>   The type of {@link Event} which is being broadcast.
   */
  public static <E extends Event> void broadcast(final E event) {
    if (listenerMap.containsKey(event.getClass()))
      ((TopicProcessor<E>)listenerMap.get(event.getClass())).onNext(event);
  }

  /**
   * Retrieves the {@link Flux stream} associated with the supplied {@link Class}.
   * @param eventType The {@link Class} of the {@link Event}.
   * @param <E>       The type of {@link Event} which parametrizes the {@link Flux}.
   * @return The {@link Flux stream} for the supplied {@link Class eventType}, null if no
   *  such {@link Flux stream} exists.
   * @throws NullPointerException if the specified key is null
   */
  public static <E extends Event> Flux<E> listen(final Class<E> eventType) {
    if (!listenerMap.containsKey(eventType))
      listenerMap.put(eventType, TopicProcessor.<E>create());
    return ((Flux<E>)listenerMap.get(eventType));
  }
}
