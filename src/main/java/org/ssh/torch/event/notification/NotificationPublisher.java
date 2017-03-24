package org.ssh.torch.event.notification;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.TopicProcessor;

/**
 * The Class NotificationPublisher.
 *
 * The class describes a {@link Publisher} of {@link Notification}.
 *
 * @author Jeroen de Jong
 */
public class NotificationPublisher implements Publisher<Notification> {

  private final TopicProcessor<Notification> topicPublisher = TopicProcessor.create();

  /**
   * Publishes a notification.
   *
   * @param notification The notification to be published.
   */
  public void addNotification(Notification notification) {
    this.topicPublisher.onNext(notification);
  }

  @Override
  public void subscribe(Subscriber<? super Notification> s) {
    topicPublisher.subscribe(s);
  }
}
