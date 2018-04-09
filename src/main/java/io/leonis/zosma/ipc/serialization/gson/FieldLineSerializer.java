package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.FieldLine;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of FieldLine objects.
 *
 * @author Ryan Meulenkamp
 */
public class FieldLineSerializer implements JsonSerializer<FieldLine> {
  @Override
  public JsonElement serialize(
      final FieldLine field,
      final Type type,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("xStart", field.getXStart());
    jsonObject.addProperty("yStart", field.getYStart());
    jsonObject.addProperty("xEnd", field.getXEnd());
    jsonObject.addProperty("yEnd", field.getYEnd());
    jsonObject.addProperty("thickness", field.getThickness());
    return jsonObject;
  }
}
