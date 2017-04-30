package org.ssh.ipc.serialization;


import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Set;
import org.reflections.Reflections;

/**
 * The Interface WriteHandler.
 *
 * This interface describes the functionality of an object which can use a {@link Writer}.
 *
 * @param <I> The object to be serialized.
 * @author Ryan Meulenkamp
 * @author Jeroen de Jong
 */
public interface WriteHandler<I extends java.io.Serializable> {

  /**
   * @return The type of object to which this {@link WriteHandler} can be applied.
   */
  default Set<Class<? extends I>> getTypes() {
    Type type = new TypeToken<I>(getClass()) { }.getType();
    return new Reflections("org.ssh").getSubTypesOf((Class<I>) type);
  }
}
