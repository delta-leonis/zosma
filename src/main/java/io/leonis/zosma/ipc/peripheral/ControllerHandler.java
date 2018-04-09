package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.game.data.PlayerCommand;
import io.leonis.zosma.ipc.peripheral.component.Stick;
import io.leonis.zosma.ipc.peripheral.component.trigger.RightTriggerSupplier;
import io.leonis.zosma.ipc.peripheral.component.xbox.RightClusterSupplier;
import java.util.function.Function;

/**
 * The Class ControllerHandler.
 *
 * Transforms a controller into a {@link PlayerCommand}.
 *
 * @author Jeroen de Jong
 */
public final class ControllerHandler<C extends Stick.LeftSupplier & Stick.RightSupplier & RightClusterSupplier & RightTriggerSupplier>
    implements Function<C, PlayerCommand> {

  @Override
  public PlayerCommand apply(final C controller) {
    return new PlayerCommand.State(
        -1f * controller.getLeftStick().getX(),
        -1f * controller.getLeftStick().getY(),
        controller.getRightStick().getX(),
        controller.isAPressed() ? 1f : 0f,
        controller.isBPressed() ? 1f : 0f,
        controller.getRightTrigger());
  }
}
