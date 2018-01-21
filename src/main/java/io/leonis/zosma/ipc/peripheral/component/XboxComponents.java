package io.leonis.zosma.ipc.peripheral.component;

import io.leonis.zosma.ipc.peripheral.component.bumper.BumperSupplier;
import io.leonis.zosma.ipc.peripheral.component.trigger.TriggerSupplier;
import io.leonis.zosma.ipc.peripheral.component.xbox.*;

/**
 * The Interface XboxComponents.
 *
 * A sum type of all components found on a typical xbox controller.
 *
 * @author Jeroen de Jong
 */
public interface XboxComponents extends RightClusterSupplier, BumperSupplier, Dpad.Supplier,
    BackSupplier, StartSupplier, TriggerSupplier, Stick.Supplier {

}
