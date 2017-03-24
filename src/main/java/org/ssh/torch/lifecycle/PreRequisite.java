package org.ssh.torch.lifecycle;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

/**
 * The Class PreRequisite.
 *
 * This class describes a pre-requisite, that is a thread which must be called
 * and completed before continuing.
 *
 * @author Jeroen De Jong
 */
@AllArgsConstructor
public class PreRequisite implements Runnable {
    /**
     * The name of the pre-requisite.
     */
    String name;
    /**
     * The runnable which is executed.
     */
    @Delegate
    Runnable runnable;

    @Override
    public String toString() {
        return name;
    }
}
