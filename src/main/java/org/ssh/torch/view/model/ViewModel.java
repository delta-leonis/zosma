package org.ssh.torch.view.model;

/**
 * The Interface ViewModel.
 *
 * This interface describes the functionality of a view-model, which is a model which can be
 * rendered by torch.
 *
 * @param <O> The type of object which is described by this view-model.
 * @author Jeroen de Jong
 */
public interface ViewModel<O> {

  /**
   * @return The object which is described by this view-model.
   */
  O getObject();
}
