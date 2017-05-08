package org.ssh.benchmarks;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.Delegate;
import reactor.core.publisher.TopicProcessor;
import reactor.test.publisher.TestPublisher;

/**
 * The Class Probeable.
 *
 * This class represents a {@link Probeable} which emits data collected from a probe.
 *
 * @author Jeroen de Jong
 */
public class ProbeablePublisher<T> implements Probeable {
  @Getter
  private final Map<String, TopicProcessor> probeProcessors = new HashMap<>();
  @Delegate
  private TestPublisher<T> publisher = TestPublisher.create();

  ProbeablePublisher() {
  }
}
