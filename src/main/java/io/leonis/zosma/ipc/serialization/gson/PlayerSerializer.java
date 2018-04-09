package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.Player;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Player objects.
 *
 * @author Ryan Meulenkamp
 */
public class PlayerSerializer implements JsonSerializer<Player> {
  @Override
  public JsonElement serialize(
      final Player src,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", src.getId());
    jsonObject.addProperty("timeStamp", src.getTimestamp());
    jsonObject.addProperty("x", src.getX());
    jsonObject.addProperty("y", src.getY());
    jsonObject.addProperty("orientation", src.getOrientation());
    jsonObject.add("teamColor", context.serialize(src.getTeamColor()));
    return jsonObject;
  }
}
