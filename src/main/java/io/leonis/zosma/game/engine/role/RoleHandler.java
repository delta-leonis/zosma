package io.leonis.zosma.game.engine.role;

import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function7;
import java.util.Set;

/**
 * @author jeroen.dejong.
 */
public interface RoleHandler extends
    Function7<Game, AllegianceTuple<Team>, AllegianceTuple<Set<MovingPlayer>>, MovingBall, Field, AllegianceTuple<Goal>, MovingPlayer, PlayerCommand> {

  @Override
  PlayerCommand apply(Game game, AllegianceTuple<Team> teams,
      AllegianceTuple<Set<MovingPlayer>> players, MovingBall ball, Field field,
      AllegianceTuple<Goal> goals, MovingPlayer agent) throws Exception;
}
