package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.Field;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Field objects.
 *
 * @author Ryan Meulenkamp
 */
public class FieldSerializer implements JsonSerializer<Field> {
  @Override
  public JsonElement serialize(
      final Field field,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("width", field.getWidth());
    jsonObject.addProperty("length", field.getLength());
    jsonObject.add("lines", context.serialize(field.getLines()));
    jsonObject.add("arcs", context.serialize(field.getArcs()));
    return jsonObject;
  }
}