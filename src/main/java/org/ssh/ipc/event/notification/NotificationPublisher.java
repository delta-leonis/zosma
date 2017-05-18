package org.ssh.ipc.event.notification;

import org.reactivestreams.*;
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
  public void addNotification(final Notification notification) {
    this.topicPublisher.onNext(notification);
  }

  @Override
  public void subscribe(final Subscriber<? super Notification> subscriber) {
    topicPublisher.subscribe(subscriber);
  }
}
