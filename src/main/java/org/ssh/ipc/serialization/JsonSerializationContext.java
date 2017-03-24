package org.ssh.ipc.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import java.util.Arrays;
import lombok.experimental.Delegate;
import org.reflections.Reflections;

/**
 * Class used to serialize object to JSON.
 */
public class JsonSerializationContext
    implements SerializationContext<String, JsonSerializationContext, JsonSerializable> {

  /**
   * The {@link Gson} object, actually used to serialize objects to JSON.
   */
  @Delegate
  private final GsonBuilder gsonBuilder = new Reflections("org.ssh")
      .getSubTypesOf(JsonSerializable.class).stream()
      .flatMap(aClass -> Arrays.stream(aClass.getDeclaredClasses()))
      .filter(aClass -> Arrays.stream(aClass.getInterfaces())
          .anyMatch(JsonSerializer.class::isAssignableFrom))
      .reduce(
          new GsonBuilder().setPrettyPrinting(),
          (builder, serializerClass) -> {
            try {
              final JsonSerializer serializer = ((Class<JsonSerializer>) serializerClass)
                  .newInstance();
              new Reflections("org.ssh")
                  .getSubTypesOf(serializerClass.getDeclaringClass())
                  .forEach(aClass -> builder.registerTypeAdapter(aClass, serializer));
              return builder;
            } catch (InstantiationException | IllegalAccessException e) {
              throw new JsonSerializable.JsonSerializableCollectionException();
            }
          },
          (u, t) -> t
      );

  @Override
  public String serialize(final JsonSerializable object) {
    return gsonBuilder.create().toJson(object);
  }
}
