package org.ssh.torch.lifecycle;

import org.reactivestreams.Publisher;

import java.util.List;

/**
 * The Interface PreRequisiteManager.
 *
 * This interface describes the functionality of a pre-requisite manager, that is a manager
 * which {@link Publisher publishes} pre-requisites.
 *
 * @author Jeroen de Jong
 */
public interface PreRequisiteManager extends Publisher<PreRequisite> {
    /**
     * Add prerequisite.
     *
     * @param message      the message
     * @param prerequisite the prerequisite
     */
    default void addPrerequisite(String message, Runnable prerequisite){
        this.addPrerequisite(new PreRequisite(message, prerequisite));
    }

    /**
     * Add prerequisite.
     *
     * @param preRequisite the pre requisite
     */
    void addPrerequisite(PreRequisite preRequisite);

    /**
     * Gets pre requisites.
     *
     * @return the pre requisites
     */
    List<PreRequisite> getPreRequisites();

    /**
     * Publish.
     *
     * @param preRequisite the pre requisite
     */
    void publish(PreRequisite preRequisite);
}
