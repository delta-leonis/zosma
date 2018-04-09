package io.leonis.zosma.ipc.controllers;

import com.studiohartman.jamepad.*;
import io.leonis.zosma.game.data.PlayerCommand;
import io.reactivex.functions.Function;

/**
 * The Class ControllerHandler.
 *
 * Transforms a controller into a {@link PlayerCommand}.
 *
 * @author Jeroen de Jong
 */
public final class ControllerHandler implements Function<ControllerIndex, PlayerCommand> {

  @Override
  public PlayerCommand apply(final ControllerIndex controllerIndex) throws Exception {
    return new PlayerCommand.State(
        -1f * controllerIndex.getAxisState(ControllerAxis.LEFTX),
        -1f * controllerIndex.getAxisState(ControllerAxis.LEFTY),
        controllerIndex.getAxisState(ControllerAxis.RIGHTX),
        controllerIndex.isButtonJustPressed(ControllerButton.A) ? 1f : 0f,
        controllerIndex.isButtonJustPressed(ControllerButton.B) ? 1f : 0f,
        controllerIndex.getAxisState(ControllerAxis.TRIGGERRIGHT));
  }
}
