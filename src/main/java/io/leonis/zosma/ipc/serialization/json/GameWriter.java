package io.leonis.zosma.ipc.serialization.json;

import com.google.gson.*;
import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.serialization.gson.*;
import java.util.function.Function;

/**
 * @author Jeroen de Jong
 */
public class GameWriter<G extends MovingPlayer.SetSupplier & Goal.SetSupplier & Field.Supplier & MovingBall.SetSupplier & Referee.Supplier & Temporal>
    implements Function<G, String> {
  private final Gson gson;

  public GameWriter() {
    this.gson = new GsonBuilder()
        .registerTypeAdapter(MovingPlayer.class, new MovingPlayerSerializer())
        .registerTypeAdapter(MovingBall.class, new MovingBallSerializer())
        .registerTypeAdapter(FieldArc.class, new FieldArcSerializer())
        .registerTypeAdapter(FieldLine.class, new FieldLineSerializer())
        .registerTypeAdapter(Field.class, new FieldSerializer())
        .registerTypeAdapter(Referee.class, new RefereeSerializer())
        .registerTypeAdapter(Team.class, new TeamSerializer())
        .registerTypeAdapter(Goal.class, new GoalSerializer())
        .create();
  }

  @Override
  public String apply(final G game) {
    return gson.toJson(game);
  }
}
