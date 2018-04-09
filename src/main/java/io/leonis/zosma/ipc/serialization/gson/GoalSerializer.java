package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.Goal;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Goal objects.
 *
 * @author Ryan Meulenkamp
 */
public class GoalSerializer implements JsonSerializer<Goal> {
  @Override
  public JsonElement serialize(
      final Goal src,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("x", src.getX());
    jsonObject.addProperty("y", src.getY());
    jsonObject.addProperty("width", src.getGoalDimension().getWidth());
    jsonObject.addProperty("depth", src.getGoalDimension().getDepth());
    jsonObject.addProperty("teamColor", src.getTeamIdentity().getColor());
    jsonObject.add("fieldHalf", context.serialize(src.getFieldHalf()));
    return jsonObject;
  }
}
