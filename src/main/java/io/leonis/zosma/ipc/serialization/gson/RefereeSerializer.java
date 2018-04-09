package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.Referee;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Referee objects.
 *
 * @author Ryan Meulenkamp
 */
public class RefereeSerializer implements JsonSerializer<Referee> {
  @Override
  public JsonElement serialize(
      final Referee src,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("timeStamp", src.getTimestamp());
    jsonObject.add("coarseStage", context.serialize(src.getCoarseStage()));
    jsonObject.addProperty("timeLeftInStage", src.getTimeLeftInStage());
    jsonObject.add("command", context.serialize(src.getCommand()));
    jsonObject.add("teams", context.serialize(src.getTeams()));
    jsonObject.addProperty("commandTimeStamp", src.getCommandTimeStamp());
    jsonObject.addProperty("commandCount", src.getCommandCount());
    return jsonObject;
  }
}
