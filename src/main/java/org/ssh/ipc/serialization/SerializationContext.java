package org.ssh.ipc.serialization;

/**
 * Class used to serialize objects.
 *
 * @param <R> The return type of this SerializationContext's serialize method.
 * @param <C> the type parameter
 * @param <O> The Object to be serialized.
 * @author Ryan Meulenkamp
 */
public interface SerializationContext<R, C extends SerializationContext, O extends Serializable<R, C>> {

  /**
   * Method used to serialize an object.
   *
   * @param object The object to serialize.
   * @return The result of serialization.
   */
  R serialize(final O object);
}
