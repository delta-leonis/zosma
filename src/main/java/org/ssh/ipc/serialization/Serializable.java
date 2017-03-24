package org.ssh.ipc.serialization;

/**
 * Interface for serializable objects.
 *
 * @param <O> the type parameter
 * @param <C> the type parameter
 * @author Ryan Meulenkamp
 */
public interface Serializable<O, C extends SerializationContext> extends java.io.Serializable {

  /**
   * Method for serializing this object.
   *
   * @param serializationContext the serialization context
   * @return The serialized form of this object.
   */
  O serialize(final C serializationContext);
}
