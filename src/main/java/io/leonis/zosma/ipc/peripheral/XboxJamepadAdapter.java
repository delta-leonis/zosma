package io.leonis.zosma.ipc.peripheral;

import com.studiohartman.jamepad.*;
import io.leonis.zosma.ipc.peripheral.Controller.ControllerIdentity;
import io.leonis.zosma.ipc.peripheral.component.*;
import java.util.function.Function;

/**
 * The Class XboxJamepadAdapter.
 *
 * Adapts a generic {@link ControllerIndex} to {@link XboxController}.
 *
 * @author Jeroen de Jong
 */
public class XboxJamepadAdapter implements Function<ControllerIndex, XboxController> {

  /**
   * {@inheritDoc}
   */
  @Override
  public XboxController apply(final ControllerIndex controller) {
    try {
      return new XboxController(
          controller.getName(),
          new ControllerIdentity(controller.getIndex()),
          new Dpad(
              controller.isButtonPressed(ControllerButton.DPAD_UP),
              controller.isButtonPressed(ControllerButton.DPAD_DOWN),
              controller.isButtonPressed(ControllerButton.DPAD_LEFT),
              controller.isButtonPressed(ControllerButton.DPAD_RIGHT)),
          new Stick(
              controller.getAxisState(ControllerAxis.RIGHTX),
              controller.getAxisState(ControllerAxis.RIGHTY),
              controller.isButtonPressed(ControllerButton.RIGHTSTICK)),
          new Stick(
              controller.getAxisState(ControllerAxis.LEFTX),
              controller.getAxisState(ControllerAxis.LEFTY),
              controller.isButtonPressed(ControllerButton.LEFTSTICK)),
          controller.isButtonJustPressed(ControllerButton.A),
          controller.isButtonJustPressed(ControllerButton.B),
          controller.isButtonJustPressed(ControllerButton.X),
          controller.isButtonJustPressed(ControllerButton.Y),
          controller.isButtonJustPressed(ControllerButton.LEFTBUMPER),
          controller.isButtonJustPressed(ControllerButton.RIGHTBUMPER),
          controller.isButtonJustPressed(ControllerButton.BACK),
          controller.isButtonJustPressed(ControllerButton.START),
          controller.getAxisState(ControllerAxis.TRIGGERLEFT),
          controller.getAxisState(ControllerAxis.TRIGGERRIGHT));
    } catch (ControllerUnpluggedException exception) {
      // This exception can be ignored because only connected controllers are supplied.
      return null;
    }
  }
}
