package io.leonis.zosma.ipc.peripheral;

import io.leonis.zosma.ipc.peripheral.component.*;
import lombok.Value;

/**
 * The Class XboxController.
 *
 * A simple container for all button states of a xbox controller.
 *
 * @author Jeroen de Jong
 */
@Value
public class XboxController implements XboxComponents, Controller {

  private final String name;
  private final ControllerIdentity identity;
  private final Dpad dpad;
  private final Stick leftStick, rightStick;
  private final boolean aPressed, bPressed, xPressed, yPressed;
  private final boolean leftBumperPressed, rightBumperPressed, backPressed, startPressed;
  private final float leftTrigger, rightTrigger;
}
