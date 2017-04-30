package org.ssh.ipc.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Modifier;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.ssh.ipc.serialization.WriteHandler;
import org.ssh.ipc.serialization.Writer;
import org.ssh.math.function.LambdaExceptions;

/**
 * The Class JsonWriter.
 *
 * This class describes an object which searches for every WriteHandler in
 * <pre>org.ssh.ipc.serialization.json</pre> and uses those to write objects provided to
 * {@link #write(Object)} as JSON.
 *
 * @author Jeroen de Jong
 */
@Slf4j
public class JsonWriter implements Writer<String> {
  /** Internal gson class used for writing the objects. */
  private final Gson gson;

  public JsonWriter() {
    this.gson = new Reflections("org.ssh.ipc.serialization.json")
        .getSubTypesOf(JsonWriteHandler.class).stream()
        .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
        .filter(clazz -> !clazz.isInterface())
        .map(LambdaExceptions.rethrowFunction(Class::newInstance))
        .peek(serializer -> log.info(
            "Registered {} for {}.",
            serializer.getClass().getSimpleName(),
            serializer.getTypes()))
        .reduce(new GsonBuilder(),
            (builder, serializer) -> {
              serializer.getTypes().forEach(type ->
                  builder.registerTypeAdapter((Class<?>) type, serializer));
              return builder;
            },
            (a, b) -> b).create();
  }

  @Override
  public <I> String write(I input) {
    return this.gson.toJson(input);
  }
}
