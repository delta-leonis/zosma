package org.ssh.torch.event;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.TopicProcessor;

/**
 * The Class TorchEventPublisher.
 *
 * This class describes a {@link Publisher} of {@link TorchEvent}.
 *
 * @author Thomas Hakkers
 */
public class TorchEventPublisher implements Publisher<TorchEvent<?>> {

  private final TopicProcessor<TorchEvent<?>> topicPublisher = TopicProcessor.create();

  /**
   * Adds a {@link TorchEvent}.
   *
   * @param torchEvent the torch event
   */
  public void addTorchEvent(TorchEvent<?> torchEvent) {
    this.topicPublisher.onNext(torchEvent);
  }

  @Override
  public void subscribe(Subscriber<? super TorchEvent<?>> s) {
    topicPublisher.subscribe(s);
  }
}